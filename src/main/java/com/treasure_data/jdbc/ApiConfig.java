package com.treasure_data.jdbc;

import com.treasure_data.client.Constants;

import java.util.Properties;

/**
 * TD API configuration
 */
public class ApiConfig
{
    public final String scheme; // http or https
    public final String endpoint;
    public final int port;
    public final boolean useSSL;
    public final Option<ProxyConfig> proxy;

    public ApiConfig(String endpoint, Option<Integer> port, boolean useSSL, Option<ProxyConfig> proxy)
    {
        this.scheme = useSSL ? "https://" : "http://";
        this.endpoint = endpoint == null? "api.treasuredata.com" : endpoint;
        this.port = port.getOrElse(useSSL ? 443 : 80);
        this.useSSL = useSSL;
        this.proxy = proxy;
    }

    public Properties toProperties() {
        Properties prop = new Properties();
        prop.setProperty(Config.TD_JDBC_USESSL, Boolean.toString(useSSL));
        prop.setProperty(com.treasure_data.client.Constants.TD_API_SERVER_HOST, endpoint);
        prop.setProperty(Constants.TD_API_SERVER_PORT, Integer.toString(port));
        if(proxy.isDefined()) {
            Properties proxyProp = proxy.get().toProperties();
            prop.putAll(proxyProp);
        }
        return prop;
    }

    public static class ApiConfigBuilder {
        public String endpoint;
        public Option<Integer> port = Option.empty();
        public boolean useSSL = false;
        public Option<ProxyConfig> proxy = Option.empty();

        public ApiConfigBuilder() {}

        public ApiConfigBuilder(ApiConfig config) {
            this.endpoint = config.endpoint;
            this.port = Option.of(config.port);
            this.useSSL = config.useSSL;
            this.proxy = config.proxy;
        }

        public ApiConfigBuilder setEndpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public ApiConfigBuilder setPort(int port) {
            this.port = Option.of(port);
            return this;
        }

        public ApiConfigBuilder setUseSSL(boolean useSSL) {
            this.useSSL = useSSL;
            return this;
        }

        public ApiConfigBuilder setProxyConfig(ProxyConfig proxyConfig) {
            this.proxy = Option.of(proxyConfig);
            return this;
        }

        public ApiConfig createApiConfig() {
            return new ApiConfig(endpoint, port, useSSL, proxy);
        }
    }
}