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
package org.jivesoftware.smackx.jinglerawudp.packet;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smackx.jingle.nat.TransportCandidate;
import org.jivesoftware.smackx.jingle.packet.JingleTransport;

/**
 * Raw UDP profile.
 */
public class JingleRawUdpTransport extends JingleTransport {
    public static final String NAMESPACE = "urn:xmpp:jingle:transports:raw-udp:1";

    public JingleRawUdpTransport() {
        super(NAMESPACE);
    }

    /**
     * Add a transport candidate
     *
     * @see org.jivesoftware.smackx.jingle.packet.JingleTransport#addCandidate(org.jivesoftware.smackx.jingle.packet.JingleTransport.JingleTransportCandidate)
     */
    public void addCandidate(final JingleTransportCandidate candidate) {
        candidates.clear();
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
        if (superCandidatesList.size() > 0) {
            copy.add(superCandidatesList.get(0));
        }

        return copy;
    }

    /**
     * Raw-udp transport candidate.
     */
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

            if (transportCandidate != null && transportCandidate instanceof TransportCandidate.Fixed) {
                TransportCandidate.Fixed tcf = (TransportCandidate.Fixed) transportCandidate;

                buf.append(" generation=\"").append(tcf.getGeneration()).append("\"");
                buf.append(" ip=\"").append(tcf.getIp()).append("\"");
                buf.append(" port=\"").append(tcf.getPort()).append("\"");

                // Optional parameters
                String name = tcf.getName();
                if (name != null) {
                    buf.append(" name=\"").append(name).append("\"");
                }
            }
            return buf.toString();
        }

    }
}
