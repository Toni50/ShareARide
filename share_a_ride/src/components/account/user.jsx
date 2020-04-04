import React, {useState, useEffect} from 'react'
import {useHistory, useParams} from 'react-router-dom';


import 'date-fns';
import RepositoryService from "../../custom-axios/repository";
import Reviews from "./reviews";
import Cookie from "js-cookie";


const User = (props) => {


    //todo dont send user password on api call...use DTO
    //here and in account
    const [userInfo, setUserInfo] = React.useState({
        email:"",
        firstName:"",
        lastName:"",
        password:"",
        profilePictureUrl:"",
        phoneNumber:"",
        homeTown:"",
        country:"",
    });
    const {userId} = useParams();

    useEffect(() => {
        RepositoryService.getUser(userId)
            .then((response) => {
                console.log("getUser")
                console.log(response);
                if (response.status === 200) {
                    setUserInfo(response.data)
                }
            }).catch(RepositoryService.handleServerError);
    }, []);



    function showReviews() {
        return (
            <div>
                <Reviews userId={userId}/>
            </div>
        )
    }

    function showProfile() {
        return (
            <div>
                {userInfo.email}<br/>
                {userInfo.firstName}<br/>
                {userInfo.lastName}<br/>
                {userInfo.password}<br/>
                pic url:{userInfo.profilePictureUrl}<br/>
                {userInfo.phoneNumber}<br/>
                {userInfo.homeTown}<br/>
                {userInfo.country}<br/>
            </div>
        )
    }

    function showView() {
        return (
            <div>
                <div className="row"
                     style={{background: "linear-gradient(#0067ed, #4faaff)"}}>

                    <div className="col-sm-12">
                        <img
                            style={{width: "200px", height: "200px", margin: "auto"}}
                            src="/user.svg"/>

                        <div style={{fontSize: "30px", color: "white"}}>
                            {userInfo.firstName + " " + userInfo.lastName}
                        </div>
                    </div>
                </div>
                <br/>
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <nav>
                                <div className="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                    <a className="nav-item nav-link active" id="nav-profile-tab" data-toggle="tab"
                                       href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="true">
                                        Profile</a>
                                    <a className="nav-item nav-link" id="nav-reviews-tab" data-toggle="tab"
                                       href="#nav-reviews" role="tab" aria-controls="nav-reviews" aria-selected="false">
                                        Reviews</a>

                                </div>
                            </nav>
                            <div className="tab-content" id="nav-tabContent">
                                <div className="tab-pane fade show active" id="nav-profile" role="tabpanel"
                                     aria-labelledby="nav-profile-tab">
                                    {showProfile()}


                                </div>
                                <div className="tab-pane fade" id="nav-reviews" role="tabpanel"
                                     aria-labelledby="nav-reviews-tab">
                                    {showReviews()}
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }

    return (
        <div>
            {showView()}
        </div>
    );


};


export default User;