import React, {useState, useEffect} from 'react'

import MyModalViewRideDetails from "../modal/myModalViewRideDetails";
import {formatDate} from "../../reusable/utils"
import ModalWriteReview from "../modal/modalWriteReview";

const RideItem = (props) => {

    const [modalObj, setModalObj] = useState({shown:false,data:{}});
    const [modalObjReview, setModalObjReview] = useState({shown:false,data:{}});


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
        <div className="shadow bg-white rounded"
             style={{margin: '20px'}}>

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

                #{props.data.requestId}  <br/>
                Requested: {formatDate(props.data.timeRequested)}


                <div className="col-sm-4" style={{textAlign:"left"}}>
                    <div style={{fontWeight: "bold"}}>
                        {props.data.ride.carRideDetails.driver.firstName+' '+
                        props.data.ride.carRideDetails.driver.lastName}
                    </div>
                    {props.data.ride.carRideDetails.name}
                </div>
                <div className="col-sm-4" style={{fontWeight: "bold", textAlign: "right"}}>
                    {props.data.ride.pricePerSeat + " MKD"}<br/>
                    {props.data.ride.carRideDetails.freeSeats}
                    <img style={{width: "30px", height: "30px"}}
                         src="/seat.svg" />
                </div>
            </div>

            <div className="row">
                <div className="col-sm-12" style={{textAlign:"left",marginLeft:"10px"}}>
                {formatDate(props.data.ride.startTime)}
                </div>
            </div>

            {
                showRoute()
            }

            <div className="row" style={{margin:"10px"}}>
                <div className="col-sm-6" style={{marginBottom:"10px"}}>
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
                    >Show details
                    </button>
                </div>
                <div className="col-sm-6" style={{marginBottom:"10px"}}>
                    <button className="btn btn-primary">Cancel </button>
                </div>

                {showWriteReviewButton()}
            </div>






            {setModalViewRideDetails()}
            {setModalWriteReview()}
        </div>
    );

    function showWriteReviewButton() {
        let now = new Date();
        if(new Date(props.data.ride.startTime) < now &&
            props.data.responseStatus==="ACCEPTED"){
            return (
                <div className="col-sm-6" style={{marginBottom:"10px"}}>
                    <button className="btn btn-primary"
                            onClick={() =>{
                                let data = props.data;
                                setModalObjReview(
                                    {
                                        shown:true,
                                        data:{
                                            ...data
                                        }
                                    }
                                )
                            }}>
                        Write review
                    </button>
                </div>
            )
        }else{
            return
        }
    }

    function showRoute() {
        return (
            <div className="row" style={{textAlign:"left",marginLeft:"0px"}}>
                <div className="col-sm-12">
                       <span style={{height: "25px", width: "25px",
                           background: 'green', borderRadius: "50%"
                       }}>&nbsp; &nbsp; &nbsp;</span>&nbsp;
                    {
                        props.data.ride.rideRoute.locations[0].locationCode
                            .replace(/&/g, ' ')
                    }
                </div>
                <div className="col-sm-12">
                      <span style={{height: "25px", width: "25px",
                          background: 'red', borderRadius: "50%"
                      }}>&nbsp; &nbsp; &nbsp;</span>&nbsp;
                    {
                        props.data.ride.rideRoute.locations[
                        props.data.ride.rideRoute.locations.length-1
                            ].locationCode
                            .replace(/&/g, ' ')
                    }
                </div>
            </div>
        )
    }
    function setModalViewRideDetails() {
        return (
            <div>
                <MyModalViewRideDetails
                    data={modalObj.data}
                    show={modalObj.shown}
                    onHide={() => setModalObj(
                        {shown:false, data:{}}
                    )}
                />
            </div>
        )
    }

    function setModalWriteReview() {
        return (
            <div>
                <ModalWriteReview
                    data={modalObjReview.data}
                    show={modalObjReview.shown}
                    onHide={() => setModalObjReview(
                        {shown:false, data:{}}
                    )}
                />
            </div>
        )
    }
};



/*
todo finish
UpcomingRideItem.propTypes = {
    data: PropTypes.instanceOf(UpcomingRideModel).isRequired,
};*/

export default RideItem;