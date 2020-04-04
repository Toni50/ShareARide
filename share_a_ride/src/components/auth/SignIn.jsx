import React from 'react'
import {useHistory} from 'react-router-dom';
import 'date-fns';
import Cookie from "js-cookie"
import RepositoryService from "../../custom-axios/repository";

const SignIn = (props) => {

    const history = useHistory();

    const [user, setUser] = React.useState({
        username: "",
        password: ""
    });


    function handleChange(e) {

        let attrName = e.target.name;
        let attrVal = e.target.value;

        setUser((prevState) => {
            return {...prevState, [attrName]: attrVal}
        })
    }

    function SignIn() {
        RepositoryService.logIn(user)
            .then((response) => {
                console.log("log in response");
                console.log(response);
                if (response.status === 200) {
                    let jwt = response.data.jwt;
                    Cookie.set("jwt", jwt);
                    history.push("/")
                } else {
                    console.log(response);
                    alert("Problem occured");
                }
            }).catch(error => {
                console.log(error.response.data.message);
                alert("Problem message: "+error.response.data.message);
        });
    }


    function isSignInDisabled() {
        return user.username.length === 0 || user.password.length===0
    }

    return (
        <div className="container">
            <div className="form" style={{margin:"auto",maxWidth:"500px",marginTop:"20px"}}>
                <h3 className="text-center text-primary">
                    Sign In
                </h3>

                <div className="form-group">
                    <div className="input-group">
                        <div className="input-group-prepend">
                            <span className="input-group-text">
                                <span className="fa fa-user ">
                                </span>
                            </span>
                        </div>
                        <input type="text" className="form-control"
                               name="username"
                               value={user.username}
                               onChange={handleChange}
                               placeholder="Username"
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

                <button className="btn btn-primary"
                        disabled={isSignInDisabled()}
                        onClick={SignIn}>Sign In
                </button>
            </div>


            <span>Don't have an account?  &nbsp;
                <a href={"/register"}>Register</a>
            </span>



        </div>
    );


};


export default SignIn;