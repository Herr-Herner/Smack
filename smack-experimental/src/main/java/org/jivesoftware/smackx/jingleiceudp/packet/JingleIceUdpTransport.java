/**
 *
 * Copyright 2003-2005 Jive Software.
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
package org.jivesoftware.smackx.jingleiceudp.packet;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smackx.jingle.nat.ICECandidate;
import org.jivesoftware.smackx.jingle.nat.TransportCandidate;
import org.jivesoftware.smackx.jingle.packet.JingleTransport;

/**
 * RTP-ICE profile
 */
public class JingleIceUdpTransport extends JingleTransport {
    public static final String NAMESPACE = "urn:xmpp:jingle:transports:ice-udp:1";

    public JingleIceUdpTransport() {
        super(NAMESPACE);
    }

    /**
     * Add a transport candidate
     *
     * @see org.jivesoftware.smackx.jingle.packet.JingleTransport#addCandidate(org.jivesoftware.smackx.jingle.packet.JingleTransport.JingleTransportCandidate)
     */
    public void addCandidate(final JingleTransportCandidate candidate) {
        super.addCandidate(candidate);
    }

    /**
     * Get the list of candidates. As a "raw-udp" transport can only contain
     * one candidate, we use the first in the list...
     *
     * @see org.jivesoftware.smackx.jingle.packet.JingleTransport#getCandidates()
     */
    public List<JingleTransportCandidate> getCandidatesList() {
        List<JingleTransportCandidate> copy = new ArrayList<JingleTransportCandidate>();
        List<JingleTransportCandidate> superCandidatesList = super.getCandidatesList();
        for (int i = 0; i < superCandidatesList.size(); i++) {
            copy.add(superCandidatesList.get(i));
        }

        return copy;
    }

    public static class Candidate extends JingleTransportCandidate {
        /**
         * Default constructor
         */
        public Candidate() {
            super();
        }

        /**
         * Constructor with a transport candidate.
         */
        public Candidate(final TransportCandidate tc) {
            super(tc);
        }

        /**
         * Get the elements of this candidate.
         */
        protected String getChildElements() {
            StringBuilder buf = new StringBuilder();

            if (transportCandidate != null) {// && transportCandidate instanceof ICECandidate) {
                ICECandidate tci = (ICECandidate) transportCandidate;

                // We convert the transportElement candidate to XML here...
                buf.append(" generation=\"").append(tci.getGeneration()).append("\"");
                buf.append(" ip=\"").append(tci.getIp()).append("\"");
                buf.append(" port=\"").append(tci.getPort()).append("\"");
                buf.append(" network=\"").append(tci.getNetwork()).append("\"");
                buf.append(" username=\"").append(tci.getUsername()).append("\"");
                buf.append(" password=\"").append(tci.getPassword()).append("\"");
                buf.append(" preference=\"").append(tci.getPreference()).append("\"");
                buf.append(" type=\"").append(tci.getType()).append("\"");

                // Optional elements
                if (transportCandidate.getName() != null) {
                    buf.append(" name=\"").append(tci.getName()).append("\"");
                }
            }

            return buf.toString();
        }

    }
}
