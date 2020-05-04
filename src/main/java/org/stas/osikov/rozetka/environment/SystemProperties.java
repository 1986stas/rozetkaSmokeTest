package org.stas.osikov.rozetka.environment;

import org.aeonbits.owner.Config;

import static org.stas.osikov.rozetka.environment.PropertyConstants.CONFIG;

@Config.Sources(value = {"classpath:" + CONFIG})
public interface SystemProperties extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("url")
    @DefaultValue("https://rozetka.com.ua")
    String url();

    @Key("browser_size")
    @DefaultValue("1920x1080x24")
    String browserSize();


    @Key("headless")
    @DefaultValue("true")
    Boolean isHeadless();
}
