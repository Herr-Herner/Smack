/**
 *
 * Copyright 2003-2005 Jive Software. 2014 Florian Schmaus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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
package org.jivesoftware.smackx.jinglertp.packet;

import org.jivesoftware.smackx.jingle.packet.JingleContentDescription;
import org.jivesoftware.smackx.jingle.packet.JinglePayloadType;

public class JingleRtpContentDescription extends JingleContentDescription {

    public static final String NAMESPACE = "urn:xmpp:tmp:jingle:apps:rtp";

    /**
     * Utility constructor, with a JinglePayloadType
     */
    public JingleRtpContentDescription(final JinglePayloadType pt) {
        addJinglePayloadType(pt);
    }

    public String getNamespace() {
        return NAMESPACE;
    }

}
