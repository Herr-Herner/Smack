Jingle
======

**XEP related:** [XEP-0116: Jingle](http://xmpp.org/extensions/xep-0166.html)

Jingle Element Structure
------------------------

```
jingle
│  action (REQUIRED)
│  initator (RECOMMENDED for session initiate, NOT RECOMMENDED otherwise)
│  responder (RECOMMENDED for session accept, NOT RECOMMENDED otherwise)
│  sid (REQUIRED)
│
├──reason
│  │
│  └──(alternative─session│busy│..)
│
└──content
   │  creator (REQUIRED)
   │  disposition (OPTIONAL)
   │  name (REQUIRED)
   │  senders (OPTIONAL, except when content-modify then REQUIRED)
   │
   ├──description
   │  │  media
   │  │  xmlns
   │  │
   │  ├──payload─type
   │  │
   │  └──file (XEP─0234)
   │
   └──transport
      │  xmlns
      │  pwd
      │  ufrag
      │  mode (XEP-0234)
      │  sid (XEP-0234)
      │
      └──candidate
            component
            foundation
            generation
            id
            ip
            network
            port
            priority
            protocol
            type
```
