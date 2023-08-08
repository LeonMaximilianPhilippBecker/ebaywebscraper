const dbName = "productDB";
const collectionName = "products";

db = db.getSiblingDB(dbName);

db.createCollection(collectionName, {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: [
        "productname",
        "url",
        "mostrecentlisting",
        "requiredkeywords", "blacklistedkeywords", "maxviableprice", "maxrealisticprice", "productlistings"
      ],
      properties: {
        productname: {
          bsonType: "string",
          description: "productname is required. Must be a string"
        },
        url: {
          bsonType: "string",
          description: "url is required. Must be a string"
        },
        mostrecentlisting: {
          bsonType: "date",
          description: "mostrecentlisting is required. Must be a date"
        },
        requiredkeywords: {
          bsonType: "array",
          description: "requiredkeywords is required. Must be an array of strings",
          items: {
            bsonType: "string",
            description: ""
          }
        },
        blacklistedkeywords: {
          bsonType: "array",
          description: "blacklistedkeywords is required. Must be an array of strings",
          items: {
            bsonType: "string",
            description: ""
          }
        },
        maxviableprice: {
          bsonType: "double",
          description: "maxviableprice is required. Must be a double. Can't be negative",
          minimum: 0
        },
        maxrealisticprice: {
          bsonType: "double",
          description: "maxvrealisticprice is required. Must be a double. Can't be negative",
          minimum: 0
        },
        productlistings: {
          bsonType: "array",
          description: "",
          items: {
            bsonType: "object",
            required: [
              "listingdate",
              "listingurl",
              "price",
              "isbuyable"
            ],
            properties: {
              listingdate: {
                bsonType: "date",
                description: "listingdate is required. Must be a date"
              },
              listingurl: {
                bsonType: "string",
                description: "listingurl is required. Must be a string"
              },
              price: {
                bsonType: "double",
                description: "price is required. Must be a double. Can't be negative",
                minimum: 0
              },
              isbuyable: {
                bsonType: "bool",
                description: "isbuyable is required. Must be a bool"
              }
            }
          }
        }
      }
    },
  },
});

db[collectionName].insertMany([
  {
    productname: "Ryzen 7 5800x",
    url: "https://www.kleinanzeigen.de/s-pc-zubehoer-software/preis:80:/ryzen-7-5800x/k0c225",
    mostrecentlisting: new Date("2023-08-08T00:00:00Z"),
    requiredkeywords: ["5800x", "Ryzen"],
    blacklistedkeywords: ["defekt", "kaputt", "3d"],
    maxviableprice: Number(119.99),
    maxrealisticprice: Number(119.99),
    productlistings: [],
    isbuyable: true
  },
  {
    productname: "Ryzen 9 5900x",
    url: "https://www.kleinanzeigen.de/s-pc-zubehoer-software/preis:80:/ryzen-9-5900x/k0c225",
    mostrecentlisting: new Date("2023-08-08T00:00:00Z"),
    requiredkeywords: ["5900x", "Ryzen"],
    blacklistedkeywords: ["defekt", "kaputt", "3d"],
    maxviableprice: Number(159.99),
    maxrealisticprice: Number(79.99),
    productlistings: [],
    isbuyable: true
  },
]);
