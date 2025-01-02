FROM logstash:8.12.1

# OpenSearch 플러그인 설치
RUN bin/logstash-plugin install --version 2.0.0 logstash-output-opensearch

# 설정 파일을 이미지에 복사
COPY pipeline/logstash.conf /usr/share/logstash/pipeline/logstash.conf