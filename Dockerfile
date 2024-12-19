ARG VERSION
FROM docker.elastic.co/elasticsearch/elasticsearch:7.14.1
RUN elasticsearch-plugin install analysis-nori