import React, {useState, useEffect} from 'react'
import {Modal} from 'react-bootstrap'
import {formatDate} from "../../reusable/utils"
import RepositoryService from "../../custom-axios/repository";


const MyModalConfirmRideRequest = (props) => {

    const [seatsRequested, setSeatsRequested] = useState(1);


    function handleSeatsRequested(e) {
        if(e.target.value>= 1 && e.target.value <= calculateFreeSeats()){
            setSeatsRequested(e.target.value)
        }else{
            alert("Invalid value")
        }
    }
    function calculateTotal() {
        return props.data.pricePerSeat*seatsRequested
    }




    function ConfirmRequest() {
        let data = {
            "requestId":-1,
            "ride":{
                "rideId":props.data.rideId
            },
            "seatsRequested": seatsRequested,
            "timeRequested":new Date() ,
            "responseStatus":"PENDING",
            "responseDescription":""
        };

        RepositoryService.addRideRequest(data)
            .then((response) => {
                console.log(response);
                if(response.status===200){
                    alert("RideRequestPosted");
                    props.onHide()
                }else{
                    alert("Problem occured");
                }
            }).catch(RepositoryService.handleServerError);
    }

    function calculateFreeSeats() {
        return props.data.carRideDetails.freeSeats
    }

    function showData() {
        let view;

        {/*console.log("MyModalConfirmRideRequest")
        console.log(props)*/}

        if(props.show){
            view = <div>

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
                             src="/seat.svg" />
                    </div>
                </div>

                <div className="row">
                    <div className="col-sm-5"
                         style={{marginLeft:"20px",textAlign: "left"}}>
                        {formatDate(props.data.startTime)}
                    </div>
                    <div className="col-sm-6" style={{textAlign: "right"}}>

                        <div className="form-group row">
                            <label htmlFor="example-number-input"
                                   className="col-2 col-form-label">
                                Seats requested
                            </label>
                            <div className="col-10">
                                <input className="form-control"
                                       type="number"
                                       value={seatsRequested}
                                       id="example-number-input"
                                       onChange={handleSeatsRequested}

                                       min="1" max={calculateFreeSeats()}
                                />
                            </div>
                        </div>

                    </div>
                </div>

                Total:{calculateTotal()+"MKD"}
                <button className="btn btn-primary"
                        onClick={ConfirmRequest}>
                    Confirm Request
                </button>

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
                   Confirm Ride Request
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>

                {showData()}




            </Modal.Body>
            <Modal.Footer>
                {/*todo remove this*/}
                {/*<p className="container">{JSON.stringify(props.data)}</p>*/}
            </Modal.Footer>
        </Modal>
    );


};



export default MyModalConfirmRideRequest;