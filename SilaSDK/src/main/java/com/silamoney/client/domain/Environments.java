package com.silamoney.client.domain;

/**
 *
 * @author Karlo Lorenzana
 */
public class Environments {
    
    private Environments(){
        
    }

    /**
     * Enum used to get accepted Sila environments.
     */
    public enum SilaEnvironment {

        /**
         * Sila sandbox environment.
         */
        SANDBOX("https://sandbox.silamoney.com/0.2"),
        /**
         * Sila production environment.
         */
        PRODUCTION("https://api.silamoney.com/0.2");

        private final String url;

        /**
         * Gets the selected environment url.
         *
         * @return environment url.
         */
        public String getUrl() {
            return url;
        }

        private SilaEnvironment(String url) {
            this.url = url;
        }
    }
}
