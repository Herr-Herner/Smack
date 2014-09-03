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
package org.jivesoftware.smackx.jingle.packet;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.jingle.nat.TransportCandidate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A jingle transport extension
 *
 * @author Alvaro Saurin <alvaro.saurin@gmail.com>
 */
public class JingleTransport implements PacketExtension {

    public static final String ELEMENT = "transport";

    protected final String namespace;

    protected final List<JingleTransportCandidate> candidates = new ArrayList<JingleTransportCandidate>();

    public JingleTransport(String namespace) {
        this.namespace = namespace;
    }

    /**
     * Adds a transport candidate.
     *
     * @param candidate the candidate
     */
    public void addCandidate(final JingleTransportCandidate candidate) {
        if (candidate != null) {
            synchronized (candidates) {
                candidates.add(candidate);
            }
        }
    }

    /**
     * Get an iterator for the candidates
     *
     * @return an iterator
     */
    public Iterator<JingleTransportCandidate> getCandidates() {
        return Collections.unmodifiableList(getCandidatesList()).iterator();
    }

    /**
     * Get the list of candidates.
     *
     * @return The candidates list.
     */
    public List<JingleTransportCandidate> getCandidatesList() {
        ArrayList<JingleTransportCandidate> res = null;
        synchronized (candidates) {
            res = new ArrayList<JingleTransportCandidate>(candidates);
        }
        return res;
    }

    /**
     * Get the number of transport candidates.
     *
     * @return The number of transport candidates contained.
     */
    public int getCandidatesCount() {
        return getCandidatesList().size();
    }

    /**
     * Returns the XML element name of the element.
     *
     * @return the XML element name of the element.
     */
    public String getElementName() {
        return ELEMENT;
    }

    /**
     * Get the namespace.
     *
     * @return The namespace
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Return the XML representation for this element.
     */
    public String toXML() {
        StringBuilder buf = new StringBuilder();

        buf.append("<").append(getElementName()).append(" xmlns=\"");
        buf.append(getNamespace()).append("\" ");

        synchronized (candidates) {
            if (getCandidatesCount() > 0) {
                buf.append(">");
                Iterator<JingleTransportCandidate> iter = getCandidates();

                while (iter.hasNext()) {
                    JingleTransportCandidate candidate = iter.next();
                    buf.append(candidate.toXML());
                }
                buf.append("</").append(getElementName()).append(">");
            } else {
                buf.append("/>");
            }
        }

        return buf.toString();
    }

    /**
     * Candidate element in the transport. This class acts as a view of the
     * "TransportCandidate" in the Jingle space.
     *
     * @author Alvaro Saurin
     * @see TransportCandidate
     */
    public static abstract class JingleTransportCandidate {

        public static final String NODENAME = "candidate";

        // The transport candidate contained in the element.
        protected TransportCandidate transportCandidate;

        /**
         * Creates a new TransportNegotiator child.
         */
        public JingleTransportCandidate() {
            super();
        }

        /**
         * Creates a new TransportNegotiator child.
         *
         * @param candidate the jmf transport candidate
         */
        public JingleTransportCandidate(final TransportCandidate candidate) {
            super();
            setMediaTransport(candidate);
        }

        /**
         * Returns the XML element name of the element.
         *
         * @return the XML element name of the element.
         */
        public static String getElementName() {
            return NODENAME;
        }

        /**
         * Get the current transportElement candidate.
         *
         * @return the transportElement candidate
         */
        public TransportCandidate getMediaTransport() {
            return transportCandidate;
        }

        /**
         * Set the transportElement candidate.
         *
         * @param cand the transportElement candidate
         */
        public void setMediaTransport(final TransportCandidate cand) {
            if (cand != null) {
                transportCandidate = cand;
            }
        }

        /**
         * Get the list of attributes.
         *
         * @return a string with the list of attributes.
         */
        protected String getChildElements() {
            return null;
        }

        /**
         * Obtain a valid XML representation of a trancport candidate
         *
         * @return A string containing the XML dump of the transport candidate.
         */
        public String toXML() {
            StringBuilder buf = new StringBuilder();
            String childElements = getChildElements();

            if (transportCandidate != null && childElements != null) {
                buf.append("<").append(getElementName()).append(" ");
                buf.append(childElements);
                buf.append("/>");
            }

            return buf.toString();
        }
    }

    // Subclasses

}
