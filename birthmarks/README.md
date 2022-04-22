# pochi birthmarks module

This module defines the format of birthmarks.

## Formats

### Format of birthmarks

```js
[
  {
    "name": "ClassName",      // name of class, or program.
    "type": "birthmark_type", // type of birthmarks
    "location": "extracted birthmarks from url", // location of this birthmark load from.
    "element-type": "string", // available values: string, int, double 
    "elements": [             
      "element1", "element2", ...
    ]
  }, ...
]

### Formats of comparison results

```js
[
  {
    "left": {
      "name": "ClassName",
      "location": "extracted birthmarks from url"
    },
    "right": {
      "name": "ClassName",
      "location": "extracted birthmarks from url"
    },
    "value": 0.75 // similarity between left and right birthmarks.
  },
  ...
]
```