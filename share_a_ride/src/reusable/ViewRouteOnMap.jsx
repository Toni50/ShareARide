import React, {useEffect, useState} from 'react';
import { Map } from '@esri/react-arcgis';

import setRef from "@material-ui/core/utils/setRef";
import BermudaTriangle from "./myMap";
import CustomMap from "./CustomMap";




const ViewRouteOnMap = (props) => {



    function showLocationsOnMap() {
        console.log("ViewRouteOnMap props")
        console.log(props)
    }


    return (
        <div className="row" style={{marginTop:"10px"}}>
            {showLocationsOnMap()}
            <div className="col-sm-12"
                 style={{width: "500px", height: "500px"}}>
                <Map
                    style={{
                        boxShadow:"0 0 4px 4px #3375d6"
                    }}
                    viewProperties={{
                        center: [
                            props.data.locations[0].longitude,
                            props.data.locations[0].latitude
                        ],
                        zoom: 13
                    }}>
                    <CustomMap
                        data={props.data.locations}

                    />
                </Map>
            </div>

        </div>
    );
}
export default ViewRouteOnMap;