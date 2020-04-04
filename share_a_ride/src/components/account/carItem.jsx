import React, {useState, useEffect} from 'react'


const CarItem = (props) => {

    function showIcons() {
        let con1,con2,con3;
        let myStyle={width: "35px", height:"35px",marginRight:"15px"}

        if(props.data.smokingAllowed){
            con1=(<img style={myStyle}
                        src="/smoking_allowed.svg"/>)
        }else{
            con1=
                (<img style={myStyle}
                      src="/smoking_not_allowed.svg"/>)
        }
        if(props.data.petsAllowed){
            con2=(<img style={myStyle}
                src="/pets_allowed.svg"/>)
        }else {
            con2=( <img style={myStyle}
                         src="/pets_not_allowed.svg"/>)
        }
        if(props.data.airConditioner){
            con3=(<img style={myStyle}
                        src="/air_conditioner.svg"/>)
        }else {
            con3=( <img style={myStyle}
                         src="/no_air_conditioner.svg"/>)
        }
        return (
            <div>
               {con1}
               {con2}
               {con3}
            </div>
        )
    }
    let activeCarStyle = {}
    if(props.data.active){
        activeCarStyle = {border:"2px solid blue"}
    }

    return (
        <div className="shadow bg-white rounded"

             style={{margin: '20px',...activeCarStyle}}>
            <div className="row">
                <img className="col-sm-3" style={{width: "100px", height: "100px"}}
                     src="/user.svg"/>

                <div className="col-sm-9">

                    <div className="row">
                        <div className="col-sm-5"
                             style={{textAlign: "left"}}>
                            <span style={{fontSize: "20px", fontWeight: "bold"}}>
                                {props.data.name}
                            </span><br/>
                            {props.data.color}
                        </div>

                        <div className="col-sm-5"
                             style={{textAlign: "right", fontSize: "15px", margin: "10px"}}>
                            {props.data.freeSeats}
                            <img style={{width: "30px", height: "30px"}}
                                 src="/seat.svg"/>
                        </div>
                    </div>

                    <div className="row">
                        {showIcons()}
                    </div>
                </div>
            </div>
            <button className="btn btn-primary"
                    onClick={() =>{
                        props.EditCar(props.data.carId)
                    }}
            >Edit car
            </button>
        </div>
    );


};

export default CarItem;