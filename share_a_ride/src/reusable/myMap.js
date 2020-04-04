import React, { useState, useEffect } from 'react';
import { loadModules } from 'esri-loader';

const MyMap = (props) => {

    // let loc = {latitude:evt.mapPoint.latitude, longitude: evt.mapPoint.longitude,locationCode:"Address Not Found"}

    const [locations, setLocations] = useState([]);

    useEffect(() => {
        loadModules([
            'esri/widgets/Search/LocatorSearchSource',
            'esri/tasks/Locator',
            'esri/Graphic',
            "esri/widgets/Search"]).then(([
     LocatorSearchSource, Locator,Graphic,Search]) => {


            const search = new Search({

            });
            props.view.ui.add(search, {
                position: "top-left",
                index: 2
            });

            search.on("select-result", function(event){
                //Fires when a search result is selected.
                console.log("select-result");
                console.log(event);

                console.log(event.target.searchTerm);
                console.log("Location name:"+event.result.name+" "+event.result.feature.attributes.City+" "+event.result.feature.attributes.StAddr);
                console.log(event.result.feature.geometry.latitude);
                console.log(event.result.feature.geometry.longitude);


                props.view.center=[event.result.feature.geometry.longitude,event.result.feature.geometry.latitude]
                props.view.zoom=14;

                {/* todo add graphic after location search
                var pointSearchedLocation = {
                    type: "point",
                    longitude:  event.result.feature.geometry.longitude,
                    latitude: event.result.feature.geometry.latitude
                };
                var pointSearchedLocationSymbol = {
                    type: "simple-marker",
                    color: [180, 229, 40],  // green
                    outline: {
                        color: [0, 0, 0],
                        width: 2
                    }
                };
                var pointSearchedLocationGraphic = new Graphic({
                    geometry: pointSearchedLocation,
                    symbol: pointSearchedLocationSymbol
                });
                props.view.graphics.add(pointSearchedLocationGraphic);
                */}
            });



            {/*let loc  = new LocatorSearchSource({countryCode:"US"});
            console.log("loc")
            console.log(loc)
            search.sources.push(loc);
            //https://developers.arcgis.com/javascript/latest/api-reference/esri-widgets-Search.html#sources
            */}

            props.view.on("click", function(evt){
                if (search.activeSource) {
                    var geocoder = search.activeSource.locator; // World geocode service
                    var params = {
                        location: evt.mapPoint,
                    };



                    geocoder.locationToAddress(params)
                        .then(function(response) { // Show the address found
                              
                            console.log("response");
                            let cc = response.attributes.CountryCode;
                            let city = response.attributes.City;
                            let locality = response.attributes.Neighborhood;
                            let street = response.attributes.Address;
                            if(cc==''){
                                cc='notFound'
                            }
                            if(city==''){
                                city='notFound'
                            }
                            if(locality==''){
                                locality='notFound'
                            }
                            if(street==''){
                                street='notFound'
                            }

                            console.log(response)
                            let loc = {latitude:evt.mapPoint.latitude,
                                longitude: evt.mapPoint.longitude,
                                locationCode:cc+"&"+city+"&"+locality+"&"+street}
                            setLocations(prevState => [...prevState, loc]);


                            console.log("address:"+response)
                        }, function(err) { // Show no address found
                            let loc = {latitude:evt.mapPoint.latitude, longitude: evt.mapPoint.longitude,locationCode:"Address Not Found"}
                            setLocations(prevState => [...prevState, loc]);
                            console.log("No address found.:")
                        });
                }
            })
        }).catch((err) => console.error(err));

        return function cleanup() {
            {/*props.view.graphics.remove(graphic); */}
            props.view.graphics.removeAll();
        };
    }, []);

    useEffect(() => {

        console.log("locations");
        console.log(locations);
        loadModules([
            'esri/Graphic',
            "esri/widgets/Search"]).then(([Graphic,Search]) => {

            var simpleMarkerSymbol = {
                type: "simple-marker",
                color: [226, 119, 40],  // orange
                outline: {color: [0, 0, 0],width: 2}
            };

            var polyline = {
                type: "polyline",
                paths: []
            };

            var polylineGraphic = new Graphic({
                geometry: polyline,
                symbol: simpleLineSymbol
            });

            props.view.graphics.removeAll();
            for (const [index, item] of [...locations].entries()){
                console.log(item);

                if(index==0){
                    simpleMarkerSymbol.color= [0, 255, 0] ;
                }else if(index==locations.length-1){
                    simpleMarkerSymbol.color= [255,0 , 0] ;
                }else {
                    simpleMarkerSymbol.color=[220, 220, 220] ;
                }

                var pointLoc = {type: "point",longitude: item.longitude,
                    latitude: item.latitude};

                var graphic = new Graphic({
                    geometry:pointLoc,
                    symbol:simpleMarkerSymbol })


                polyline.paths.push([item.longitude, item.latitude])


                props.view.graphics.add(graphic);
            }

            var simpleLineSymbol = {
                type: "simple-line",
                color: [128, 128, 128], // orange
                width: 2
            };
            var polylineGraphic = new Graphic({
                geometry: polyline,
                symbol: simpleLineSymbol
            });
            props.view.graphics.add(polylineGraphic);

            props.onRouteChange(locations)

        }).catch((err) => console.error(err));



        props.onRef(method)

    }, [locations]);

    function method() {
        return function (indexDelete) {
            //dont write in the parent function of this function
            let data = locations.filter((item,index)=>{
                return index !== indexDelete;
            });
            setLocations([...data])
        }
    }

    return null;

};

export default MyMap;