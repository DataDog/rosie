# Rosie Protocol

Rosie communicates using JSON. All inputs and outputs are JSON documents.

## Request

The request has the following structure

- `language`: language of the code submitted
- `code`: base64 encoding of the code
- `codeEncoding`: encoding of the code. This is optional, by default, the server will usse UTF-8
- `rules`: array of rules to check

### Rules

Each rules is defined as below:

- `id`: the identifier of the rule to check

### Example

```json
{
  "code": "ICAgICAgICByID0gcmVxdWVzdHMuZ2V0KHcsIHZlcmlmeT1GYWxzZSkKICAgICAgICByID0gcmVxdWVzdHMuZ2V0KHcsIHZlcmlmeT1GYWxzZSwgdGltZW91dD0xMCk=",
  "codeEncoding": "utf-8",
  "rules": [
    {
      "id": "ruleset/myrule",
      "language": "python",
      "type": "FunctionCall",
      "ruleCode": "ICAgICAgICBmdW5jdGlvbiB2aXNpdChub2RlKSB7CiAgICAgICAgICAgIHZhciBoYXNUaW1lb3V0ID0gZmFsc2U7CiAgICAgICAgICAgIGZvciAodmFyIGkgPSAwIDsgaSA8IG5vZGUuYXJndW1lbnRzKCkuc2l6ZSgpIDsgaSsrKXsKICAgICAgICAgICAgICAgIGNvbnN0IGFyZ3VtZW50ID0gbm9kZS5hcmd1bWVudHMoKS5nZXQoaSk7CiAgICAgICAgICAgICAgICBpZihhcmd1bWVudC5uYW1lKCkuaXNQcmVzZW50KCkgJiYgYXJndW1lbnQubmFtZSgpLmdldCgpID09ICJ0aW1lb3V0IikgewogICAgICAgICAgICAgICAgICAgIGhhc1RpbWVvdXQgPSB0cnVlOwogICAgICAgICAgICAgICAgfQogICAgICAgICAgICB9CiAgICAgICAgICAgIGlmKCFoYXNUaW1lb3V0KXsKICAgICAgICAgICAgICAgIHJlcG9ydEVycm9yKG5vZGUubGluZSgpLCAidGltZW91dCBub3QgZGVmaW5lZCIsICJDUklUSUNBTCIsICJTQUZFVFkiKTsKICAgICAgICAgICAgfQogICAgICAgIH0="
    }
  ]
}
```

## Response

A response is composed with

- `result`: for each rule, the analysis result of the rule
- `error`: general errors from the service itself

List of general errors:

- `code-not-base64`: the code being based (base64) is invalid
- `rule-not-base64`: the rule is not encoded in base64
- `invalid-request`: elements missing in the request
- `language-not-supported`: language not supported
- `unavailable`: the server is not available (overloaded)

The result contains all the violations for each rule. Each rule reports:

- `id`: the identifier of the rule
- `violations`: the list of violations
- `errors`: list of errors when processing the rule
- `executionError`: a string that reports the VM error when processing the code

At the rule level, the errors are:

- `language-mismatch`: mismatch between the rule language and the code language
- `rule-timeout`: the rule did not execute within the allocated time
- `error-unknown`: unknown error
- `error-execution`: the code of the rule is not correct and the string `executionError` contains a message

For each `violation`, we the response contains:

- `start` and `end`: the starting position of the violation (where to put a marker in the IDE)
- `severity`:  a number between 1 (highest critically) and 4 (least critical) that indicates the severity of the issue.
- `category`: category of the issues
- `fixes`: list of fixes.

List of violations categories:

- `Documentation`
- `Performance`
- `Deployment`
- `Design`
- `Security`
- `Safety`
- `BestPractices`
- `CodeStyle`
- `ErrorProne`
- `Unknonwn`

A fix is defined by:

- `description`: a description of the fix
- `edits`: a list of edits to apply the fix. The edits must be applied in sequence

An edit is defined by:

- `action`: the type of edit (add, delete, replace)
- `start`: where the edit should start
- `end`: where the edit should end (only for `delete` and `replace`)
- `content`: the value to add/replace (should be `null` for the `delete` action)

### Example 1

Example of a response for two rules. The first rule has one violation with one fix that is doing two edits.
The second action has incorrect code and did not process.

```json
{
  "result": [
    {
      "id": "ruleset/myrule",
      "violations": [
        {
          "start": {
            "line": 3,
            "col": 5
          },
          "end": {
            "line": 4,
            "col": 2
          },
          "severity": 2,
          "category": "best_practices",
          "description": "do not do XXXX",
          "fixes": [
            {
              "description": "add correct parameter",
              "edits": [
                {
                  "action": "add",
                  "start": {
                    "line": 3,
                    "col": 5
                  },
                  "content": "foobar"
                },
                {
                  "action": "delete",
                  "start": {
                    "line": 3,
                    "col": 5
                  },
                  "end": {
                    "line": 3,
                    "col": 10
                  }
                }
              ]
            }
          ]
        }
      ],
      "errors": [
      ]
    },
    {
      "id": "ruleset2/myrule",
      "violations": [
      ],
      "errors": [
        "invalid-rule-code"
      ]
    }
  ]
}
```

### Example 2

In this second example, there is one general error: the code did not decode from the base64.

```json
{
  "result": [
  ],
  "errors": [
    "invalid-code"
  ]
}
```
