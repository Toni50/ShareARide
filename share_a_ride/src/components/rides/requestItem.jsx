import React, {useState, useEffect} from 'react'

import MyModalRespondToRideRequest from "../modal/myModalRespondToRideRequest";
import {formatDate} from "../../reusable/utils"
import MyModalViewRideDetails from "../modal/myModalViewRideDetails";

const RequestItem = (props) => {

    const [modalObjRespond, setModalObjRespond] = useState({shown:false,data:{}});
    const [modalObjRideDetails, setModalObjRideDetails] = useState({shown:false,data:{}});

    let response;
    if(props.data.responseStatus==="ACCEPTED"){
        response  = (<span className="badge badge-success"
                           style={{fontSize:'20px',color:"white"}}>
                        {props.data.responseStatus}
                    </span>);
    }else if(props.data.responseStatus==="REJECTED"){
        response  = (<span className="badge badge-danger"
                           style={{fontSize:'20px',color:"white"}}>
                        {props.data.responseStatus}
                    </span>);
    }else if(props.data.responseStatus==="PENDING"){
        response  = (<span className="badge badge-warning"
                           style={{fontSize:'20px',color:"white"}}>
                        {props.data.responseStatus}
                    </span>);
    }



    return (

        <div className="shadow bg-white rounded" style={{margin: '20px'}}>


            <div className="row">
                <div className="col-sm-6">
                </div>
                <div className="col-sm-4">
                    {response}
                </div>
            </div>


            <div className="row">

                <img className="col-sm-3" style={{width: "70px", height: "70px"}}
                     src="/user.svg" />

                #{props.data.requestId}


                <div className="col-sm-9" style={{textAlign: "left"}}>
                    <span style={{fontWeight: "bold"}}>
                        {props.data.requestByUser.firstName+' '+
                        props.data.requestByUser.lastName}
                    </span>
                    <span style={{marginLeft:"40px"}}>
                           Requested: {formatDate(props.data.timeRequested)}
                    </span><br/>

                    {
                        showRequestText()
                    }

                </div>
            </div>

            <div className="row" style={{marginTop:"20px"}}>
                <div className="col-sm-6" style={{marginBottom:"20px"}}>
                    <button className="btn btn-primary"
                            onClick={() =>{
                                let data = props.data;
                                setModalObjRideDetails(
                                    {
                                        shown:true,
                                        data:{
                                            ...data
                                        }
                                    }
                                )
                            }}
                    >Show details
                    </button>
                </div>


                <div className="col-sm-6" style={{marginBottom:"20px"}}>
                    {showResponseBtn()}
                </div>
            </div>



            {setModalRespond()}
            {setModalRideDetails()}

        </div>
    );

    function showResponseBtn() {
        if(props.data.responseStatus === "PENDING"){
            return (
                <button className="btn btn-primary"
                        onClick={() =>{
                            let data = props.data;
                            setModalObjRespond(
                                {
                                    shown:true,
                                    data:{
                                        ...data
                                    }
                                }
                            )
                        }}
                >Respond
                </button>
            )
        }else return;

    }

    function showRequestText() {
        let startLocation = props.data.ride.rideRoute.locations[0].locationCode;
        startLocation = startLocation.replace(/&/g, ' ');

        let endLocation = props.data.ride.rideRoute.locations[
        props.data.ride.rideRoute.locations.length - 1].locationCode
        endLocation = endLocation.replace(/&/g, ' ');
        return (
            <div>
                {'Wants to join your ride from ' +
                startLocation +
                ' to ' +
                endLocation +
                ' on ' +
                formatDate(props.data.ride.startTime) +
                ' taking ' +
                props.data.seatsRequested +
                ' seats for ' +
                props.data.ride.pricePerSeat + " MKD" +
                ' per seat. Total: ' +
                props.data.seatsRequested * props.data.ride.pricePerSeat
                }
            </div>
        )
    }

    function setModalRespond() {
        return (
            <div>
                <MyModalRespondToRideRequest
                    data={modalObjRespond.data}
                    show={modalObjRespond.shown}
                    onHide={() => setModalObjRespond(
                        {shown:false, data:{}}
                        )}
                    RequestDataChanged={props.RequestDataChanged}
                />
            </div>
        )
    }
    function setModalRideDetails() {
        return (
            <div>
                <MyModalViewRideDetails
                    data={modalObjRideDetails.data}
                    show={modalObjRideDetails.shown}
                    onHide={() => setModalObjRideDetails(
                        {shown:false, data:{}}
                    )}
                />
            </div>
        )
    }




};


export default RequestItem;