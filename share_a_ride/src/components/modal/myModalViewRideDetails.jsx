import React, {useState, useEffect} from 'react'

import {Modal} from 'react-bootstrap'
import {forEach} from "react-bootstrap/cjs/ElementChildren";
import {formatDate} from "../../reusable/utils"
import MapHolder from "../../reusable/MapHolder";
import ViewRouteOnMap from "../../reusable/ViewRouteOnMap";

const MyModalViewRideDetails = (props) => {


    function showRouteListData() {
        return (
            <div className="col-sm-12">
                {
                    props.data.ride.rideRoute.locations.map((item, index) => {
                        let color = 'grey';
                        if (index == 0) {
                            color = 'green'
                        } else if (index === props.data.ride.rideRoute.locations.length - 1) {
                            color = 'red'
                        }
                        return (
                            <div>
                                <span style={{height: "25px", width: "25px",
                                        background: color, borderRadius: "50%"
                                    }}>&nbsp; &nbsp; &nbsp;</span>
                                &nbsp;
                                {
                                    item.locationCode.replace(/&/g, ' ')
                                }
                            </div>
                        )
                    })
                }
            </div>
        )
    }

    function showRoute() {
        return (
            <div className="row" style={{borderTop:"1px solid lightgrey",marginTop:"20px"}}>
                <ul className="nav nav-tabs" id="myTab" role="tablist"
                    style={{marginTop:"10px",marginLeft:"10px"}}>
                    <li className="nav-item">
                        <a className="nav-link active" id="list-tab" data-toggle="tab" href="#list"
                           role="tab"
                           aria-controls="list" aria-selected="true">List</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" id="map-tab" data-toggle="tab" href="#map" role="tab"
                           aria-controls="map" aria-selected="false">Map</a>
                    </li>
                </ul>
                <div className="tab-content col-sm-12" id="myTabContent">

                    <div className="tab-pane fade show active" id="list" role="tabpanel"
                         aria-labelledby="list-tab">
                        <h5 className="col-sm-12">
                            Route ({props.data.ride.rideRoute.locations.length})
                        </h5>
                        {showRouteListData()}
                    </div>
                    <div className="tab-pane fade " id="map" role="tabpanel" aria-labelledby="map-tab">
                        <ViewRouteOnMap
                            data={props.data.ride.rideRoute}
                        />
                    </div>

                </div>





            </div>
        )
    }

    function showPassengers() {
        return (
            <div className="row" style={{borderTop:"1px solid lightgrey",marginTop:"20px"}}>
                <h5 className="col-sm-12">Passengers  ({props.data.ride.passengers.length})</h5>
                <div className="col-sm-12">
                    {
                        props.data.ride.passengers.map((item) => {
                            return (
                                <div>
                                    <img style={{width: "50px", height: "50px"}}
                                         src="/user.svg" />&nbsp;
                                    {item.firstName+" "+item.lastName}
                                </div>
                            )
                        })
                    }
                </div>
            </div>
        )
    }

    function showData() {
        let view;

        if(props.show){
            view = <div>

                <div className="row">
                    <img style={{width: "70px", height: "70px"}}
                         src="/user.svg" className="col-sm-3"/>

                    <div className="col-sm-4" style={{textAlign: "left"}}>
                        <div style={{fontWeight: "bold"}}>
                            {props.data.ride.carRideDetails.driver.firstName+' '+
                            props.data.ride.carRideDetails.driver.lastName
                            }

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
                    <div className="col-sm-5" style={{marginLeft:"20px",textAlign: "left"}}>
                        {formatDate(props.data.ride.startTime)}
                    </div>
                    <div className="col-sm-6" style={{textAlign: "right"}}>
                        <button className="btn btn-primary">Cancel </button>
                    </div>
                </div>

                {showPassengers()}

                {showRoute()}
            </div>
        }


        return (
            <div>
                {view}
            </div>
        )
    }

    return (
        <Modal
            {...props}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >

            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                     Ride Details
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>

                {showData()}




            </Modal.Body>
            <Modal.Footer>
                {/*<p className="container">{JSON.stringify(props.data)}</p>*/}
            </Modal.Footer>
        </Modal>
    );


};



export default MyModalViewRideDetails;