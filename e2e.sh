#!/bin/bash
curl --location 'https://api.datad0g.com/api/v2/static-analysis/rulesets?filter%5Bname%5D=python-security' \
--header 'dd-api-key: '$DD_API_KEY_STAGING \
--header 'dd-application-key: '$DD_APP_KEY_STAGING \
--output 'rules.json'

./gradlew cli:run --args="--directory $PROJECT_DIRECTORY -t true -r rules.json -o sarif_report.sarif"
