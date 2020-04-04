import React, {useState, useEffect} from 'react'
import {Modal} from 'react-bootstrap'
import RepositoryService from "../../custom-axios/repository";


const ModalWriteReview = (props) => {

    const [review, setReview] = React.useState({});

    function handleCarChanged(e) {
        let attrName = e.target.name;
        let attrVal = e.target.value;

        if(attrName==="numOfStars"){
            if(e.target.value>= 1 && e.target.value <= 5 ){
            }else{
                alert("Invalid value")
                return
            }
        }

        setReview((prevState) => {
            return {...prevState, [attrName]: attrVal}
        })
    }


    const addReview= ()=> {
        review.dateCreated=new Date();
        RepositoryService.addReview(review)
            .then((response) => {
                props.onHide();
                setReview({});//mora posle props.onHide(); da se povika
            }).catch(RepositoryService.handleServerError);
    };


    function showData() {
        if (props.show) {
            if (JSON.stringify(review) === JSON.stringify({})) {
                setReview((prevState) => {
                    return {
                        reviewId:-1,
                        rideId:props.data.ride.rideId,
                        description:"",
                        numOfStars:5,
                        dateCreated:new Date()
                    }
                })
            }
        }

        return (
            <div>
                <div className="form-group row">
                    <label htmlFor="example-number-input"
                           className="col-3 col-form-label">
                        description
                    </label>
                    <div className="col-9">
                        <input className="form-control"
                               name={"description"}
                               value={review.description}
                               id="example-number-input"
                               onChange={handleCarChanged}
                        />
                    </div>

                    <label htmlFor="example-number-input"
                           className="col-3 col-form-label">
                        numOfStars
                    </label>
                    <div className="col-9">
                        <input className="form-control"
                               type="number"
                               name={"numOfStars"}
                               value={review.numOfStars}
                               id="example-number-input"
                               onChange={handleCarChanged}
                               min="1"
                               max="5"
                        />
                    </div>

                </div>
                <button
                    className="btn btn-primary"
                    onClick={()=>{
                        addReview()
                    }}>
                    add Review
                </button>
            </div>

        )
    }


    return (
        <Modal
            {...props}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
            onHide={() => {
                setReview({})
                props.onHide()
            }}
        >

            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Write review
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


export default ModalWriteReview;