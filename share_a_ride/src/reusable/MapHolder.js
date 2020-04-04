import React, {useEffect, useState} from 'react';
import { Map } from '@esri/react-arcgis';
import BermudaTriangle from "./myMap";
import setRef from "@material-ui/core/utils/setRef";




const MapHolder = (props) => {
    const [locations, setLocations] = useState([]);
    const [ref, SetRef] = useState(null);

    function locationsChanged(locations) {
        /*console.log("Hurray i got all locations")
        console.log(locations)*/

        setLocations(locations)
    }

    useEffect(() => {

        props.onRouteChange(locations)
    }, [locations]);

    function locationRemovedAtIndex(index) {
        console.log("Location removed at index:"+index)

        if(ref!=null){
            ref(index);
        }
    }

    function showLocationsInList() {
        let data = locations.map((item,index)=>{
            let colorClass='bg-dark';
            if(index===0){
                colorClass='bg-success';
            }else if(index===locations.length-1){
                colorClass='bg-danger';
            }
            else if(index%2===0){
                colorClass='bg-dark';
            }else{
                colorClass='bg-secondary';
            }
            return (
                <tr key={item.latitude + "" + item.longitude}
                 className={colorClass}
                >
                    <td> <span
                        style={{color: "white",fontSize:"20px"}}>
                        {item.locationCode.replace(/&/g, ' ')}
                    </span>
                         </td>
                    <td>
                        <button
                            onClick={()=>{locationRemovedAtIndex(index)}}>
                            X</button>
                    </td>
                </tr>
            )
        });
        return (
            <table className="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Location</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>

               {data}

                </tbody>
            </table>



        )
    }


    return (
        <div className="row" style={{marginTop:"10px"}}>
            <div className="col-sm-6"
                 style={{width: "500px", height: "500px"}}>
                <Map
                    style={{
                        boxShadow:"0 0 4px 4px #3375d6"
                    }}
                    viewProperties={{
                        center: [21.432264, 41.996364],
                        zoom: 14
                    }}>
                    <BermudaTriangle
                        onRouteChange={locationsChanged}
                        onRef={(ref) => {
                            SetRef(ref)
                        }
                        }
                    />
                </Map>
            </div>
            <div className="col-sm-6">
            {showLocationsInList()}
            </div>
        </div>
    );
}
export default MapHolder;