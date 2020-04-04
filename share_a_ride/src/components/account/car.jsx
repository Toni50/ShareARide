import React, {useState, useEffect} from 'react'
import 'date-fns';
import CarItem from "./carItem";
import MyModalCar from "../modal/myModalCar";
import RepositoryService from "../../custom-axios/repository";


const Car = (props) => {
    const [modalObj, setModalObj] = useState({shown: false, data: {}});
    const [cars, setCars] = React.useState([]);

    useEffect(() => {
        RepositoryService.fetchCars()
            .then((response) => {
                console.log(response)
                setCars(response.data)
            }).catch(RepositoryService.handleServerError);
    }, []);


    function EditCar(carIdEdit) {
        let car;
        car = cars.filter((item) => {
            return item.carId === carIdEdit;
        });
        setModalObj(
            {
                shown: true,
                data: {
                    ...car[0]
                }
            }
        )
    }

    function showCars() {
        return (
            cars.map((item, index) => {
                return (
                    <div
                        key={index}>
                        <CarItem
                            data={item}
                            EditCar={EditCar}
                        />
                    </div>
                )

            })
        )

    }

    function IsBtnAddCarDisabled() {
        return cars.length == 10;
    }

    return (
        <div>
            <h1>Cars</h1>

            <button className="btn btn-primary"
                    disabled={IsBtnAddCarDisabled()}
                    onClick={() => {
                        let data = {
                            "carId": -1,
                            "name": "",
                            "active": false,
                            "color": "",
                            "freeSeats": 1,
                            "petsAllowed": false,
                            "smokingAllowed": false,
                            "airConditioner": false,
                            "pictureUrl": ""
                        };

                        setModalObj(
                            {
                                shown: true,
                                data: {
                                    ...data
                                }
                            }
                        )
                    }}
            >Add car
            </button>


            {showCars()}

            {setModal()}
        </div>
    );



    function DeleteCar(carIdDelete) {
        let newCars = cars.filter((item)=>{
            return item.carId !== carIdDelete;
        });
        setCars((prevState)=>{
            return [...newCars]
        })
    }
    function CarAddedEdited(data) {
        let added=true;
        let newCars = cars.map((item)=>{
            if(item.carId===data.carId){
                added=false;
                return data
            }else{
                if(data.active){
                    item.active=false;
                }
                return item
            }
        });


        if(added){
            setCars((prevState)=>{
                return [...prevState,data]
            })
        }else{
            setCars((prevState)=>{
                return [...newCars]
            })
        }




    }
    function setModal() {
        return (
            <div>
                <MyModalCar
                    data={modalObj.data}
                    show={modalObj.shown}
                    CarAddedEdited={CarAddedEdited}
                    DeleteCar={DeleteCar}
                    onHide={() => setModalObj(
                        {shown: false, data: {}}
                    )}
                />
            </div>
        )
    }

};


export default Car;