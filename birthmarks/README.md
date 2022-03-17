# pochi birthmarks module

This module defines the format of birthmarks.

```js
[
  {
    "name": "ClassName",      // name of class, or program.
    "type": "birthmark_type", // type of birthmarks
    "location": "extracted birthmarks from", // location of this birthmark load from.
    "element-type": "string", // available values: string, int, double 
    "elements": [             
      "element1", "element2", ...
    ]
  }
]
