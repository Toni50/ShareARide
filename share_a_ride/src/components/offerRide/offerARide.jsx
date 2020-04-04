import React, {useState, useEffect} from 'react'
import {useHistory, useParams} from 'react-router-dom';



import 'date-fns';

import DateFnsUtils from '@date-io/date-fns';
import {
    MuiPickersUtilsProvider,
    KeyboardTimePicker,
    KeyboardDatePicker,
} from '@material-ui/pickers';



import * as ReactDOM from 'react-dom';
import { Map } from '@esri/react-arcgis';
import { WebMap, WebScene } from '@esri/react-arcgis';
import { Scene } from '@esri/react-arcgis';

import MapHolder from "../../reusable/MapHolder";
import RepositoryService from "../../custom-axios/repository";



const OfferARide = (props) => {

    //todo stavi gi vo eden state koj ke se prati so axios
    const history = useHistory();
    const [selectedDate, setSelectedDate] = React.useState(new Date());
    const [price, setPrice] = React.useState(400);
    const [locations, setLocations] = useState([]);


    const handleDateChange = date => {
        setSelectedDate(date);
        console.log("DateChange")
        console.log(date)
    };


    function setDateAndTimePickers() {
        return (
            <MuiPickersUtilsProvider utils={DateFnsUtils}>
                <KeyboardDatePicker
                    margin="normal"
                    id="date-picker-dialog"
                    label="Pick Date"
                    format="dd/MM/yyyy"
                    value={selectedDate}
                    onChange={handleDateChange}
                    KeyboardButtonProps={{
                        'aria-label': 'change date',
                    }}
                />
                <br/>
                <KeyboardTimePicker
                    margin="normal"
                    id="time-picker"
                    label="Pick time"
                    ampm={false}
                    value={selectedDate}
                    onChange={handleDateChange}
                    KeyboardButtonProps={{
                        'aria-label': 'change time',
                    }}
                />
                <br/>
            </MuiPickersUtilsProvider>
        )
    }

    function Offer() {

    // let loc = {latitude:evt.mapPoint.latitude,
        // longitude: evt.mapPoint.longitude,
        // locationCode:"Address Not Found"}

        if(locations.length<2){
            alert("Add ride locations")
            return
        }

        let data={
            "rideId":-1,
            "startTime":selectedDate,
            "pricePerSeat":price,
            "finished":false,
            "rideRoute":{
                "id": -1,
                "locations": locations
            },
            "passengers":[]
        };



        RepositoryService.offerRide(data)
            .then((response) => {
                console.log("addRide");
                console.log(response);

                RidePosted()
            }).catch(RepositoryService.handleServerError);

    }

    function RidePosted() {
        alert("Your ride is added");

        setSelectedDate(new Date());
        setPrice(400);
        setLocations([]);
        history.push("/");
    }



    function handlePriceChange(e) {
        setPrice(e.target.value)
    }

    function setInputPrice() {
        return (
            <div className="form-group row">
                <label htmlFor="example-number-input" className="col-2 col-form-label">Price</label>
                <div className="col-10">
                    <input className="form-control"
                           type="number"
                           value={price}
                           id="example-number-input"
                           onChange={handlePriceChange}
                    />
                </div>
            </div>
        )
    }
    function locationsChanged(locations) {
       /* console.log("Hurray i got all locations In offer a ride")
        console.log(locations)*/
        setLocations(locations)
    }

    return (
        <div>
            {setDateAndTimePickers()}
            {setInputPrice()}
            <button className="btn btn-primary"
                    onClick={Offer}>Offer A Ride</button>
            <MapHolder
                onRouteChange={locationsChanged}
            />
        </div>
    );


};


export default OfferARide;