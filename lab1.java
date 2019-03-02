var campus = /* color: #d63000 */ee.Geometry.Point([74.4094535973777, 31.470849726677333]),
    sent2 = ee.ImageCollection("COPERNICUS/S2");
    
var image = ee.Image(sent2
.filterDate("2018-01-01", "2019-01-01")
.filterBounds(campus)
.sort("CLOUD_COVERAGE_ASSESSMENT")
.first());
print("A Sentinel-2 scene:", image);

var trueColour = {
  bands: ["B8", "B4", "B3"],
  min: 0,
  max: 2500
};

Map.addLayer(image, trueColour, "false-color-composite");

var NDVI = image.expression(
  "(NIR - RED) / (NIR + RED)",
  {
    RED: image.select("B4"),
    NIR: image.select("B8"),
    BLUE: image.select("B2")
  });
  
Map.addLayer(NDVI, {min: 0, max: 1}, "NDVI");
