/**
 * Copyright 2013 Florian Schmaus
 *
 * All rights reserved. Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jivesoftware.smack.util.dns;

public class HostAddress {
    private String fqdn;
    private int port;
    
    public HostAddress(String fqdn) throws IllegalArgumentException{
        if (fqdn == null)
            throw new IllegalArgumentException("FQDN is null");
        if (fqdn.charAt(fqdn.length() - 1) == '.') {
            this.fqdn = fqdn.substring(0, fqdn.length() - 1);
        } else {
            this.fqdn = fqdn;
        }
        this.port = -1;
    }
    
    public HostAddress(String fqdn, int port) throws IllegalArgumentException {
        this(fqdn);
        if (port < 0 || port > 65535)
            throw new IllegalArgumentException (
                    "DNS SRV records weight must be a 16-bit unsiged integer (i.e. between 0-65535. Port was: " + port);
        
        this.port = port;
    }
    
    public String getFQDN() {
        return fqdn;
    }
    
    public int getPort() {
        return port;
    }
    
    public String toString() {
        if (port >= 0) {
            return fqdn + ":" + port;
        } else {
            return fqdn;
        }
    }
    
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HostAddress)) {
            return false;
        }

        final HostAddress address = (HostAddress) o;

        if (!fqdn.equals(address.fqdn)) {
            return false;
        }
        return port == address.port;
    }
}
