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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Jingle content description.
 *
 * @author Alvaro Saurin <alvaro.saurin@gmail.com>
 */
public abstract class JingleContentDescription implements PacketExtension {

    public static final String ELEMENT = "description";

    private final List<JinglePayloadType> payloads = new ArrayList<JinglePayloadType>();

    /**
     * Returns the XML element name of the element.
     *
     * @return the XML element name of the element.
     */
    public String getElementName() {
        return ELEMENT;
    }

    /**
     * Return the namespace.
     *
     * @return The namespace
     */
    public abstract String getNamespace();

    /**
     * Adds a audio payload type to the packet.
     *
     * @param pt the audio payload type to add.
     */
    public void addJinglePayloadType(final JinglePayloadType pt) {
        synchronized (payloads) {
            payloads.add(pt);
        }
    }

    /**
     * Returns an Iterator for the audio payloads in the packet.
     *
     * @return an Iterator for the audio payloads in the packet.
     */
    public Iterator<JinglePayloadType> getJinglePayloadTypes() {
        return Collections.unmodifiableList(getJinglePayloadTypesList()).iterator();
    }

    /**
     * Returns a list for the audio payloads in the packet.
     *
     * @return a list for the audio payloads in the packet.
     */
    public ArrayList<JinglePayloadType> getJinglePayloadTypesList() {
        synchronized (payloads) {
            return new ArrayList<JinglePayloadType>(payloads);
        }
    }

    /**
     * Returns a count of the audio payloads in the Jingle packet.
     *
     * @return the number of audio payloads in the Jingle packet.
     */
    public int getJinglePayloadTypesCount() {
        synchronized (payloads) {
            return payloads.size();
        }
    }

    /**
     * Convert a Jingle description to XML.
     *
     * @return a string with the XML representation
     */
    public String toXML() {
        StringBuilder buf = new StringBuilder();

        synchronized (payloads) {
            if (payloads.size() > 0) {
                buf.append("<").append(getElementName());
                buf.append(" xmlns=\"").append(getNamespace()).append("\" >");

                Iterator<JinglePayloadType> pt = payloads.listIterator();
                while (pt.hasNext()) {
                    JinglePayloadType pte = pt.next();
                    buf.append(pte.toXML());
                }
                buf.append("</").append(getElementName()).append(">");
            }
        }

        return buf.toString();
    }


}
