package ru.hostco.utils;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:general.properties"})
public interface GeneralConfig extends Config {

    String homepage();

    String login();

    String password();
}
