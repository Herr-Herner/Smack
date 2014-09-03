Jingle
======

**XEP related:** [XEP-0116: Jingle](http://xmpp.org/extensions/xep-0166.html)

Jingle Element Structure
------------------------

```
jingle
│  action
│  initator
│  responder
│  sid
│
├──reason
│  │
│  └──(alternative─session│busy│..)
│
└──content
   │  creator
   │  dispos
   │  name
   │  sender
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
