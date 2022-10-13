package io.codiga.analyzer.pattern;

import com.google.common.annotations.VisibleForTesting;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.common.Position;
import io.codiga.model.pattern.PatternObject;
import io.codiga.model.pattern.PatternVariable;
import io.codiga.model.pattern.PatternVariableValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {

    private static final Logger logger = LoggerFactory.getLogger(PatternMatcher.class);
    private String code;
    private AnalyzerRule analyzerRule;
    private List<String> codeLines;

    public PatternMatcher(String code, AnalyzerRule rule) {
        this.code = code;
        this.analyzerRule = rule;
        this.codeLines = new ArrayList<>();

        Scanner scanner = new Scanner(code);

        while (scanner.hasNextLine()) {
            this.codeLines.add(scanner.nextLine());
        }
        scanner.close();
    }

    public static String prepareStringForRegularExpression(String originalString) {
        return originalString
            .replace("(", "\\(")
            .replace(")", "\\)");
    }

    private String stripVariable(String variableName) {
        return variableName
            .replace("${", "")
            .replace("}", "");

    }

    private Position getCodePosition(int index) {
        int lineNumber = 1;
        for (String line : codeLines) {
            if (index <= line.length() + 1) {
                return new Position(lineNumber, index);
            }
            index = index - line.length() - 1;
            lineNumber = lineNumber + 1;
        }
        return null;
    }

    @VisibleForTesting
    public List<PatternVariable> getVariables() {
        ArrayList<PatternVariable> patternVariableArrayList = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\$\\{[^\\}]*\\})", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(prepareStringForRegularExpression(analyzerRule.pattern()));

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

    @VisibleForTesting
    public String getRegularExpression() {
        List<PatternVariable> patternVariables = this.getVariables().stream().sorted(new Comparator<PatternVariable>() {
            @Override
            public int compare(PatternVariable o1, PatternVariable o2) {
                return o2.start() - o1.start();
            }
        }).toList();

        String regularExpression = prepareStringForRegularExpression(this.analyzerRule.pattern());
        for (PatternVariable patternVariable : patternVariables) {
            regularExpression = regularExpression.substring(0, patternVariable.start()) + "(.+)" + regularExpression.substring(patternVariable.end(), regularExpression.length());
        }
        return regularExpression;

    }

    public List<PatternObject> getPatternObjects() {
        List<PatternObject> patternObjects = new ArrayList<>();
        String regularExpression = this.getRegularExpression();
        List<PatternVariable> patternVariables = this.getVariables();
        HashMap<String, PatternVariableValue> variables = new HashMap<>();

        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(this.code);


        while (matcher.find()) {

            for (int i = 1; i <= matcher.groupCount(); i++) {
                int startIndex = matcher.start(i);
                int endIndex = matcher.end(i);
                PatternVariable patternVariable = patternVariables.get(i - 1);
                PatternVariableValue patternPosition = new PatternVariableValue(matcher.group(i),
                    getCodePosition(startIndex + 1),
                    getCodePosition(endIndex + 1),
                    startIndex,
                    endIndex);
                variables.put(stripVariable(patternVariable.name()), patternPosition);
            }

            patternObjects.add(new PatternObject(
                getCodePosition(matcher.start(0) + 1),
                getCodePosition(matcher.end(0) + 1),
                matcher.start(0),
                matcher.end(0),
                variables));
        }
        return List.copyOf(patternObjects);
    }


}
