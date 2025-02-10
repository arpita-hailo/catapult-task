package util;

public enum TestConstants {
    URL("http://demo-api.tc.staging.catapult.com:3000/graphql");

    public final String value;
    private TestConstants(String value){this.value = value;}

}
