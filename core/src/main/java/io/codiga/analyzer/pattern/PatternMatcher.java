package io.codiga.analyzer.pattern;

import com.google.common.annotations.VisibleForTesting;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.pattern.PatternObject;
import io.codiga.model.pattern.PatternVariable;
import io.codiga.model.pattern.PatternVariableValue;
import io.codiga.utils.PositionFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {

    private static final Logger logger = LoggerFactory.getLogger(PatternMatcher.class);
    private final String code;
    private final AnalyzerRule analyzerRule;
    private final PositionFinder positionFinder;

    public PatternMatcher(String code, AnalyzerRule rule) {
        this.code = code;
        this.analyzerRule = rule;
        this.positionFinder = new PositionFinder(this.code);
    }

    // Escape parenthesis and other special characters from the string
    public static String prepareStringForRegularExpression(String originalString) {
        return originalString
            .replace("[", "\\[")
            .replace("]", "\\]")
            .replace("(", "\\(")
            .replace(")", "\\)");
    }

    private String stripVariable(String variableName) {
        return variableName
            .replace("${", "")
            .replace("}", "");

    }


    /**
     * Get the list of variables from the pattern.
     * For example, of the pattern is "bla ${foo}", we will detect
     * a variable with the name foo.
     *
     * @param rulePattern
     * @return
     */
    @VisibleForTesting
    public List<PatternVariable> getVariablesFromPattern(String rulePattern) {
        ArrayList<PatternVariable> patternVariableArrayList = new ArrayList<>();

        // Catch variables in the pattern
        Pattern pattern = Pattern.compile("(\\$\\{[^\\}]*\\})", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(prepareStringForRegularExpression(rulePattern));

        while (matcher.find()) {
            if (matcher.groupCount() != 1) {
                continue;
            }
            int start = matcher.start(0);
            int end = matcher.end(0);
            String found = matcher.group(0);

            PatternVariable patternVariable = new PatternVariable(found, start, end);
            patternVariableArrayList.add(patternVariable);
        }

        // return immutable list
        return List.copyOf(patternVariableArrayList);
    }


    /**
     * Get the regular expression to match for. We replace the variables
     * by a regular expression we can match against.
     *
     * @param patternVariables
     * @return
     */
    @VisibleForTesting
    public String getRegularExpression(List<PatternVariable> patternVariables) {
        // Sort the pattern variables in the inverse order they appear in the pattern
        List<PatternVariable> orderedPatternVariables = patternVariables.stream().sorted(new Comparator<PatternVariable>() {
            @Override
            public int compare(PatternVariable o1, PatternVariable o2) {
                return o2.start() - o1.start();
            }
        }).toList();

        String regularExpression = prepareStringForRegularExpression(this.analyzerRule.pattern());
        for (PatternVariable patternVariable : orderedPatternVariables) {
            regularExpression = regularExpression.substring(0, patternVariable.start()) + "(.+)" + regularExpression.substring(patternVariable.end());
        }
        return regularExpression.replaceAll("\\.\\.\\.", ".*");


    }

    /**
     * Get the list of pattern objects. We just get all the pattern objects
     * that match the code with their variables.
     *
     * @return
     */
    public List<PatternObject> getPatternObjects() {
        List<PatternObject> patternObjects = new ArrayList<>();
        List<PatternVariable> patternVariables = this.getVariablesFromPattern(analyzerRule.pattern());
        String regularExpression = this.getRegularExpression(patternVariables);

//        logger.info(String.format("regular expression: %s", regularExpression));
//        logger.info(String.format("code: %s", this.code));
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(this.code);


        while (matcher.find()) {
            HashMap<String, PatternVariableValue> variables = new HashMap<>();

            for (int i = 1; i <= matcher.groupCount(); i++) {
                int startIndex = matcher.start(i);
                int endIndex = matcher.end(i);
//                logger.info(String.format("start %s, end %s", startIndex, endIndex));
                PatternVariable patternVariable = patternVariables.get(i - 1);
                PatternVariableValue patternPosition = new PatternVariableValue(matcher.group(i),
                    positionFinder.getCodePosition(startIndex + 1),
                    positionFinder.getCodePosition(endIndex + 1),
                    startIndex,
                    endIndex);
                variables.put(stripVariable(patternVariable.name()), patternPosition);
            }

            patternObjects.add(new PatternObject(
                positionFinder.getCodePosition(matcher.start(0) + 1),
                positionFinder.getCodePosition(matcher.end(0) + 1),
                matcher.start(0),
                matcher.end(0),
                variables));
        }
        return List.copyOf(patternObjects);
    }


}
