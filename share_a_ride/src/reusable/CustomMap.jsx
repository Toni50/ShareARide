import React, { useState, useEffect } from 'react';
import { loadModules } from 'esri-loader';

const CustomMap = (props) => {

    useEffect(() => {
        console.log("CustomMap props");
        console.log(props);
        loadModules(['esri/Graphic']).then(([Graphic]) => {

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
            for (const [index, item] of [...props.data].entries()){
                console.log(item);

                if(index==0){
                    simpleMarkerSymbol.color= [0, 255, 0] ;
                }else if(index==props.data.length-1){
                    simpleMarkerSymbol.color= [255,0 , 0] ;
                }else {
                    simpleMarkerSymbol.color=[220, 220, 220] ;
                }

                var pointLoc = {type: "point",longitude: item.longitude,
                    latitude: item.latitude};

                var graphic = new Graphic({
                    geometry:pointLoc,
                    symbol:simpleMarkerSymbol })


                polyline.paths.push([item.longitude, item.latitude]);


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



        }).catch((err) => console.error(err));





    }, []);



    return null;

};

export default CustomMap;