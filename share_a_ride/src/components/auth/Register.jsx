import React, {useState, useEffect} from 'react'
import {useHistory, useParams} from 'react-router-dom';
import 'date-fns';
import RepositoryService from "../../custom-axios/repository";


const Register = (props) => {

    const history = useHistory()

    const [user, setUser] = React.useState({
        userId:-1,
        firstName:"",
        lastName:"",
        password: "",
        profilePictureUrl: "",
        email:"",
        phoneNumber:"",
        homeTown: "",
        country: ""
    });

    function handleChange(e) {
        let attrName = e.target.name;
        let attrVal = e.target.value;


        if(e.target.name==="phoneNumber"){
            if(e.target.value.length>= 0 && e.target.value.length <=9){
                setUser((prevState) => {
                    return {...prevState, [attrName]: attrVal}
                })
            }else{
                alert("Invalid value")
            }
            return
        }


        setUser((prevState) => {
            return {...prevState, [attrName]: attrVal}
        })
    }

    function Register() {
        RepositoryService.register(user)
            .then((data) => {
                console.log("register");
                console.log(data);
                if(data.status===200){
                    history.push("/signin")
                }else {
                    alert("Problem occured");
                }
            }).catch(error => {
                console.log(error.response.data.message);
                alert("Problem message: "+error.response.data.message);
            });
    }

    function isRegisterDisabled() {
        return user.firstName.length === 0 || user.lastName.length===0
            || user.password.length===0 || user.email.length===0
            || user.phoneNumber.length===0 || user.homeTown.length===0
            || user.country.length===0
    }

    return (
        <div className="container">


            <div className="form" style={{margin:"auto",maxWidth:"500px",marginTop:"20px"}}>
                <h3 className="text-center text-primary">
                    Register
                </h3>

                <div className="form-group">
                    <div className="input-group">
                        <div className="input-group-prepend">
                            <span className="input-group-text">
                                <span className="fa fa-user">
                                </span>
                            </span>
                        </div>
                        <input type="text" className="form-control"
                               name="firstName"
                               value={user.firstName}
                               onChange={handleChange}
                               placeholder="FirstName"
                        />
                    </div>
                </div>



                <div className="form-group">
                    <div className="input-group">
                        <div className="input-group-prepend">
                            <span className="input-group-text">
                                <span className="fa fa-user">
                                </span>
                            </span>
                        </div>
                        <input type="text" className="form-control"
                               name="lastName"
                               value={user.lastName}
                               onChange={handleChange}
                               placeholder="LastName"
                        />
                    </div>
                </div>



                <div className="form-group">
                    <div className="input-group">
                        <div className="input-group-prepend">
                            <span className="input-group-text">
                                <span className="fa fa-envelope">
                                </span>
                            </span>
                        </div>
                        <input type="text" className="form-control"
                               name="email"
                               value={user.email}
                               onChange={handleChange}
                               placeholder="Email"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <div className="input-group">
                        <div className="input-group-prepend">
                            <span className="input-group-text">
                                <span className="fa fa-lock">
                                </span>
                            </span>
                        </div>
                        <input type="password" className="form-control"
                               name="password"
                               value={user.password}
                               onChange={handleChange}
                               placeholder="Password"
                        />
                    </div>
                </div>



                <div className="form-group">
                    <div className="input-group">
                        <div className="input-group-prepend">
                            <span className="input-group-text">
                                <span className="fa fa-phone">
                                </span>
                            </span>
                        </div>
                        <input type="number"
                               className="form-control"
                               name="phoneNumber"
                               value={user.phoneNumber}
                               onChange={handleChange}
                               placeholder="PhoneNumber"

                        />
                    </div>
                </div>



                <div className="form-group">
                    <div className="input-group">
                        <div className="input-group-prepend">
                            <span className="input-group-text">
                                <span className="fa fa-building">
                                </span>
                            </span>
                        </div>
                        <input type="text" className="form-control"
                               name="homeTown"
                               value={user.homeTown}
                               onChange={handleChange}
                               placeholder="HomeTown"
                        />
                    </div>
                </div>


                <div className="form-group">
                    <div className="input-group">
                        <div className="input-group-prepend">
                            <span className="input-group-text">
                                <span className="fa fa-globe">
                                </span>
                            </span>
                        </div>
                        <input type="text" className="form-control"
                               name="country"
                               value={user.country}
                               onChange={handleChange}
                               placeholder="Country"
                        />
                    </div>
                </div>


                <button className="btn btn-primary"
                        disabled={isRegisterDisabled()}
                        onClick={Register}>Register</button>
            </div>


            <span>Already have an account? &nbsp;
                <a href={"/signin"}>SignIn</a>
            </span>




        </div>
    );


};


export default Register;