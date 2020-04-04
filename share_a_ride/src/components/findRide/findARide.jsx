import React, {useState, useEffect} from 'react'
import {useHistory, useParams} from 'react-router-dom';



import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'

import 'date-fns';

import Grid from '@material-ui/core/Grid';
import DateFnsUtils from '@date-io/date-fns';
import {
    MuiPickersUtilsProvider,
    KeyboardTimePicker,
    KeyboardDatePicker,
} from '@material-ui/pickers';
import MapHolder from "../../reusable/MapHolder";
import FindARideResults from "./findARideResults";
import RepositoryService from "../../custom-axios/repository";


const FindARide = (props) => {

    const [selectedDate, setSelectedDate] = React.useState(new Date());
    const [locations, setLocations] = useState([]);
    const history = useHistory();

    const handleDateChange = date => {
        setSelectedDate(date);
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

    function Find() {
        if(locations.length<2){
            alert("Add ride locations")
            return;
        }
        let data={
            "startTime":selectedDate,
            "locations": locations
        };

        history.push({
            pathname: '/findarideresults',
            state: data
        })



    }








    function locationsChanged(locations) {
        setLocations(locations)
    }

    return (
        <div>
            {setDateAndTimePickers()}

            <button className="btn btn-primary"
            onClick={Find}>Find A Ride</button>

            <MapHolder
                onRouteChange={locationsChanged}
            />




        </div>
    );
};


export default FindARide;