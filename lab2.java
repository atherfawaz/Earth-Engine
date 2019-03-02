var srtm = ee.Image("USGS/SRTMGL1_003"),
    geometry = /* color: #d63000 */ee.Geometry.Polygon(
        [[[128.7822350038864, -16.130273225177113],
          [129.11457142966765, -16.54144154988179],
          [129.8698814882614, -16.277971510957954],
          [129.9467857851364, -15.892670501311246],
          [128.96076283591765, -15.800193382026483]
        ]]
    );

Map.addLayer(srtm, {min: 0, max: 1000}, 'DEM'); 
Map.addLayer(geometry);
var dict = srtm.reduceRegion({
    reducer: 'mean',
    geometry: geometry,
    scale: 90}
    );      
print('Mean elevation', dict);
