import React, {useState, useEffect} from 'react'
import {Modal} from 'react-bootstrap'
import RepositoryService from "../../custom-axios/repository";


const MyModalCar = (props) => {

    const [car, setCar] = React.useState({});




    function handleCarChanged(e) {
        let attrName = e.target.name;
        let attrVal = e.target.value;
        if(attrName==='active' || attrName==='smokingAllowed' ||
            attrName==='airConditioner' || attrName==='petsAllowed'){
            if(e.target.checked){
                attrVal=true
            }else{
                attrVal=false
            }
        }
        setCar((prevState) => {
            return {...prevState, [attrName]: attrVal}
        })
    }


    function showData() {

        if (props.show) {
            if (JSON.stringify(car) === JSON.stringify({})) {

                setCar(props.data)
            }
        }


        const SaveCar= ()=> {
            RepositoryService.addCar(car)
                .then((response) => {
                    props.CarAddedEdited(response.data);
                    props.onHide();
                    setCar({});//mora posle props.onHide(); da se povika
                })
                .catch(RepositoryService.handleServerError);
                    //todo handle error user has max(10) amount of cars

        };

        const DeleteCar=()=> {
            RepositoryService.deleteCar(props.data.carId)
                .then((response) => {
                    console.log(response);
                    if(response.status===200){
                        props.DeleteCar(props.data.carId)
                        props.onHide();
                        setCar({})//mora posle props.onHide(); da se povika
                    }
                }).catch(RepositoryService.handleServerError);
        };

        function IsDeleteBtnDisabled() {
            return car.carId === -1;
        }

        return (
            <div  >

                <div className="form-group row">
                    <label htmlFor="example-number-input"
                           className="col-3 col-form-label">
                        Car name
                    </label>
                    <div className="col-9">
                        <input className="form-control"
                               name={"name"}
                               value={car.name}
                               id="example-number-input"
                               onChange={handleCarChanged}
                        />
                    </div>

                </div>



                <div className="form-group row">
                    <label htmlFor="example-number-input"
                           className="col-3 col-form-label">
                        Car color
                    </label>
                    <div className="col-9">
                        <input className="form-control"
                               name={"color"}
                               value={car.color}
                               onChange={handleCarChanged}
                        />
                    </div>
                </div>



                <div className="form-group row">
                    <label htmlFor="example-number-input"
                           className="col-3 col-form-label">
                        Free seats
                    </label>
                    <div className="col-9">
                        <input className="form-control"
                               type="number"
                               name={"freeSeats"}
                               value={car.freeSeats}
                               onChange={handleCarChanged}
                        />
                    </div>
                </div>


                <div className="form-group row">
                    <div className="col-sm-3">
                    </div>

                    <div className="custom-control custom-checkbox col-sm-9" >
                        <input type="checkbox"
                               className="custom-control-input"
                               id="active"

                               name={"active"}
                               checked={car.active}
                               onChange={handleCarChanged}
                        />
                            <label className="custom-control-label"
                                   htmlFor="active">
                                active
                            </label>
                    </div>
                </div>



                <div className="form-group row">
                    <div className="col-sm-3">
                    </div>
                    <div className="custom-control custom-checkbox col-sm-9" >
                        <input type="checkbox"
                               className="custom-control-input"
                               id="smokingAllowed"
                               name={"smokingAllowed"}
                               checked={car.smokingAllowed}
                               onChange={handleCarChanged}
                        />
                        <label className="custom-control-label"
                               htmlFor="smokingAllowed">
                            Smoking Allowed
                        </label>
                    </div>
                </div>



                <div className="form-group row">
                    <div className="col-sm-3">
                    </div>
                    <div className="custom-control custom-checkbox col-sm-9" >
                        <input type="checkbox"
                               className="custom-control-input"
                               id="airConditioner"
                               name={"airConditioner"}
                               checked={car.airConditioner}
                               onChange={handleCarChanged}
                        />
                        <label className="custom-control-label"
                               htmlFor="airConditioner">
                            airConditioner
                        </label>
                    </div>
                </div>




                <div className="form-group row">
                    <div className="col-sm-3">
                    </div>
                    <div className="custom-control custom-checkbox col-sm-9" >
                        <input type="checkbox"
                               className="custom-control-input"
                               id="petsAllowed"
                               name={"petsAllowed"}
                               checked={car.petsAllowed}
                               onChange={handleCarChanged}
                        />
                        <label className="custom-control-label"
                               htmlFor="petsAllowed">
                            petsAllowed
                        </label>
                    </div>
                </div>


                <div className="row">

                    <div className=" col-sm-6">
                        <button
                                className="btn btn-primary"
                                onClick={()=>{
                                    SaveCar()
                                }}>
                            Save
                        </button>
                    </div>

                    <div className=" col-sm-6">
                        <button
                                className="btn btn-danger col-sm-6"
                                onClick={()=>{
                                    DeleteCar()
                                }}

                                disabled={IsDeleteBtnDisabled()}
                        >Delete</button>
                    </div>

                </div>


            </div>
        )
    }

    return (
        <Modal
            {...props}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
            onHide={() =>{
                setCar({})
                props.onHide()
            }}
        >

            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Add/Edit car
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


export default MyModalCar;