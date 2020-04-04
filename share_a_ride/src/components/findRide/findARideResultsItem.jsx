import React, {useState, useEffect} from 'react'
import 'date-fns';


import DateFnsUtils from '@date-io/date-fns';
import {
    MuiPickersUtilsProvider,
    KeyboardTimePicker,
    KeyboardDatePicker,
} from '@material-ui/pickers';
import MapHolder from "../../reusable/MapHolder";
import MyModalViewRideDetails from "../modal/myModalViewRideDetails";
import MyModalConfirmRideRequest from "../modal/myModalConfirmRideRequest";
import {formatDate} from "../../reusable/utils"

const FindARideResultsItem = (props) => {
/*
    {props.data.pricePerSeat}
    {props.data.startTime}
   */
    const [modalObj, setModalObj] = useState({shown:false,data:{}});

    function setModal() {
        return (
            <div>
                <MyModalConfirmRideRequest
                    data={modalObj.data}
                    show={modalObj.shown}
                    onHide={() => setModalObj(
                        {shown:false, data:{}}
                    )}
                />
            </div>
        )
    }



    return (


        <div className="card row" style={{margin: '20px'}}>


            <div className="row">
                <img style={{width: "70px", height: "70px"}}
                     src="/user.svg" className="col-sm-3"/>

                <div className="col-sm-4" style={{textAlign: "left"}}>
                    <div style={{fontWeight: "bold"}}>
                        {props.data.carRideDetails.driver.firstName+' '+
                        props.data.carRideDetails.driver.lastName
                        }
                    </div>
                    {props.data.carRideDetails.name}
                </div>
                <div className="col-sm-4" style={{fontWeight: "bold", textAlign: "right"}}>
                    {props.data.pricePerSeat + " MKD"}<br/>
                    {props.data.carRideDetails.freeSeats}
                    <img style={{width: "30px", height: "30px"}}
                         src="/seat.svg"/>
                </div>
            </div>

            <div className="row">
                <div className="col-sm-5" style={{marginLeft: "20px", textAlign: "left"}}>
                    {
                       formatDate(props.data.startTime)

                    }
                </div>
                <div className="col-sm-6" style={{textAlign: "right"}}>


                    <button className="btn btn-primary"
                            onClick={() =>{
                                let data = props.data;
                                setModalObj(
                                    {
                                        shown:true,
                                        data:{
                                            ...data
                                        }
                                    }
                                )
                            }}
                    >Request Ride
                    </button>


                </div>
            </div>




            {setModal()}
        </div>
    );
};


export default FindARideResultsItem;