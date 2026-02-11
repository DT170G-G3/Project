ARG PAYARA_TAG=7.2026.1
FROM payara/server-full:${PAYARA_TAG}

# 1) Lägg JDBC-driver i domain-dir/lib så den finns på classpath vid boot
# Payara rekommenderar domain-dir/lib för JDBC-driver. :contentReference[oaicite:5]{index=5}
COPY --chown=payara:payara lib/mysql-connector-j.jar \
    /opt/payara/appserver/glassfish/domains/domain1/lib/mysql-connector-j.jar

# 2) Lägg postboot-fil (skrivbar, så Payara kan append:a deploy-kommandon)
COPY --chown=payara:payara config/post-boot-commands.asadmin \
    /opt/payara/config/post-boot-commands.asadmin
