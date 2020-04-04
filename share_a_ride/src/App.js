import React from 'react';

import './App.css';
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import MyRides from "./components/rides/myRides";
import Navbar from "./reusable/navbar";
import FindARide from "./components/findRide/findARide";
import OfferARide from "./components/offerRide/offerARide";
import FindARideResults from "./components/findRide/findARideResults";
import Notifications from "./components/account/notifications";
import Wallet from "./components/account/wallet";
import Car from "./components/account/car";
import Contacts from "./components/account/contacts";
import User from "./components/account/user";
import Account from "./components/account/account";
import SignIn from "./components/auth/SignIn";
import Register from "./components/auth/Register";
import {userAuthenticated} from "./reusable/utils";

function App() {

    return (

        <div className="App">
            <Router>
                <main role="main">
                    <div>
                        <Route exact path={"/"} render={() => {
                            if (userAuthenticated()) {
                                return (
                                    <div>
                                        <Navbar/>
                                        <MyRides/>
                                    </div>
                                )
                            } else {
                                return <Redirect to="/signin"/>
                            }
                        }
                        }>
                        </Route>
                        <Route exact path={"/findaride"} render={() => {
                            if (userAuthenticated()) {
                                return (
                                    <div>
                                        <Navbar/>
                                        <FindARide/>
                                    </div>
                                )
                            } else {
                                return <Redirect to="/signin"/>
                            }
                        }
                        }>
                        </Route>
                        <Route exact path={"/findarideresults"} render={(data) => {
                            if (userAuthenticated()) {
                                return (
                                    <div>
                                        <Navbar/>
                                        <FindARideResults data={data}/>
                                    </div>
                                )
                            } else {
                                return <Redirect to="/signin"/>
                            }
                        }
                        }>
                        </Route>
                        <Route exact path={"/offeraride"} render={() => {
                            if (userAuthenticated()) {
                                return (
                                    <div>
                                        <Navbar/>
                                        <OfferARide/>
                                    </div>
                                )
                            } else {
                                return <Redirect to="/signin"/>
                            }
                        }
                        }>
                        </Route>
                        <Route exact path={"/notifications"} render={() => {
                            if (userAuthenticated()) {
                                return (
                                    <div>
                                        <Navbar/>
                                        <Notifications/>
                                    </div>
                                )
                            } else {
                                return <Redirect to="/signin"/>
                            }
                        }
                        }>
                        </Route>
                        <Route exact path={"/wallet"} render={() => {
                            if (userAuthenticated()) {
                                return (
                                    <div>
                                        <Navbar/>
                                        <Wallet/>
                                    </div>
                                )
                            } else {
                                return <Redirect to="/signin"/>
                            }
                        }
                        }>
                        </Route>
                        <Route exact path={"/car"} render={() => {
                            if (userAuthenticated()) {
                                return (
                                    <div>
                                        <Navbar/>
                                        <Car/>
                                    </div>
                                )
                            } else {
                                return <Redirect to="/signin"/>
                            }
                        }
                        }>
                        </Route>
                        <Route exact path={"/contacts"} render={() => {
                            if (userAuthenticated()) {
                                return (
                                    <div>
                                        <Navbar/>
                                        <Contacts/>
                                    </div>
                                )
                            } else {
                                return <Redirect to="/signin"/>
                            }
                        }
                        }>
                        </Route>
                        <Route exact path={"/user/:userId"} render={() => {
                            if (userAuthenticated()) {
                                return (
                                    <div>
                                        <Navbar/>
                                        <User/>
                                    </div>
                                )
                            } else {
                                return <Redirect to="/signin"/>
                            }
                        }
                        }>
                        </Route>
                        <Route exact path={"/account"} render={() => {
                            if (userAuthenticated()) {
                                return (
                                    <div>
                                        <Navbar/>
                                        <Account/>
                                    </div>
                                )
                            } else {
                                return <Redirect to="/signin"/>
                            }
                        }
                        }>
                        </Route>
                        <Route exact path={"/signin"} render={() => {
                            if (userAuthenticated()) {
                                return <Redirect to="/"/>
                            } else {
                                return <SignIn/>
                            }
                        }
                        }>
                        </Route>
                        <Route exact path={"/register"} render={() => {
                            if (userAuthenticated()) {
                                return <Redirect to="/"/>
                            } else {
                                return <Register/>
                            }
                        }
                        }>
                        </Route>
                    </div>
                </main>
            </Router>
        </div>

    );
}

export default App;
