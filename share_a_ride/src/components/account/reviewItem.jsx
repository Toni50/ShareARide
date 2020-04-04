import React, {useState, useEffect} from 'react'
import 'date-fns';
import {formatDate} from "../../reusable/utils";

const ReviewItem = (props) => {

    function showNumOfStars() {
        let tmp = []
        for (let i = 0; i < props.data.numOfStars; i++) {
            tmp.push("star")
        }
        for (let i = tmp.length; i < 5; i++) {
            tmp.push("empty")
        }
        return (
            tmp.map((item) => {
                if (item === "star"){
                    return (
                        <img style={{width: "30px", height: "30px"}}
                             src="/star.svg"/>
                    )
                }else {
                    return (
                        <img style={{width: "30px", height: "30px"}}
                             src="/empty_star.svg"/>
                    )
                }
            })
        )
    }

    return (
        <div className="row">
            <img className="col-sm-3" style={{width: "70px", height: "70px"}}
                 src="/user.svg"/>

            <div className="col-sm-9" style={{textAlign: "left", fontWeight: "bold"}}>
                <a href={"/user/" + props.data.leftByUser.userId}>
                    {props.data.leftByUser.firstName + " " + props.data.leftByUser.lastName}
                </a><br/>
                {showNumOfStars()}<br/>
                {formatDate(props.data.dateCreated)}<br/>
            </div>
            <div className="col-sm-12" style={{fontWeight: "bold", textAlign: "left"}}>
                {props.data.description}<br/>
            </div>
        </div>
    );

};

export default ReviewItem;