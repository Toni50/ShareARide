import React, {useState, useEffect} from 'react'
import {Modal} from 'react-bootstrap'
import RepositoryService from "../../custom-axios/repository";


const MyModalRespondToRideRequest = (props) => {


    function AcceptRequest() {

        RepositoryService.acceptRideRequest(props.data.requestId)
            .then((response) => {
                console.log("AcceptRequest requestId"+props.data.requestId);
                console.log(response);
                if(response.status===200){
                    props.onHide();
                    props.RequestDataChanged(props.data.requestId,"ACCEPTED")
                }else{
                    alert("Problem occured");
                }
            }).catch(RepositoryService.handleServerError);
    }

    function RejectRequest() {
        RepositoryService.rejectRideRequest(props.data.requestId)
            .then((response) => {
                console.log("RejectRequest requestId"+props.data.requestId);
                console.log(response);
                if(response.status===200){
                    props.onHide();
                    props.RequestDataChanged(props.data.requestId,"REJECTED")
                }else{
                    alert("Problem occured");
                }
            }).catch(RepositoryService.handleServerError);
    }

    function showData() {
        let view;

        if(props.show){
            view = <div>

                <div className="row" style={{margin:"20px"}}>
                    <button className="btn btn-danger col-sm-4"
                    onClick={RejectRequest}>
                        Reject
                    </button>

                    <span className="col-sm-4"></span>
                    <button className="btn btn-success col-sm-4"
                            onClick={AcceptRequest}>
                        Accept
                    </button>

                </div>

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
                    Respond to ride request
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>


                {showData()}


            </Modal.Body>
            <Modal.Footer>
                {/*//todo remove this*/}
               {/* <p className="container">{JSON.stringify(props.data)}</p>*/}
            </Modal.Footer>
        </Modal>
    );


};



export default MyModalRespondToRideRequest;