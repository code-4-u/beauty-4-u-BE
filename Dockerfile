ARG VERSION
FROM opensearchproject/opensearch:2.11.0
RUN /usr/share/opensearch/bin/opensearch-plugin install analysis-nori