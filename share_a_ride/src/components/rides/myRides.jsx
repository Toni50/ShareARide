import React, {useState, useEffect} from 'react'
import {useHistory, useParams} from 'react-router-dom';

import RequestItem from "./requestItem";
import RepositoryService from "../../custom-axios/repository";
import RideItem from "./rideItem";
import ReactPaginate from 'react-paginate';
import UpcomingRides from "./upcomingRides";
import RideRequests from "./rideRequests";
import HistoryRides from "./historyRides";

const MyRides = (props) => {


    const history = useHistory();





    return (
        <div>
            <br/>
            {showTabs()}
        </div>
    );

    function showTabs() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <nav>
                            <div className="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a className="nav-item nav-link active" id="nav-upcoming-tab" data-toggle="tab"
                                   href="#nav-upcoming" role="tab" aria-controls="nav-upcoming" aria-selected="true">
                                    Upcoming</a>
                                <a className="nav-item nav-link" id="nav-requests-tab" data-toggle="tab"
                                   href="#nav-requests" role="tab" aria-controls="nav-requests" aria-selected="false">
                                    Requests</a>
                                <a className="nav-item nav-link" id="nav-history-tab" data-toggle="tab"
                                   href="#nav-history" role="tab" aria-controls="nav-history" aria-selected="false">
                                    History</a>
                            </div>
                        </nav>
                        <div className="tab-content" id="nav-tabContent">
                            <div className="tab-pane fade show active" id="nav-upcoming" role="tabpanel"
                                 aria-labelledby="nav-upcoming-tab">
                                <UpcomingRides />
                            </div>
                            <div className="tab-pane fade" id="nav-requests" role="tabpanel"
                                 aria-labelledby="nav-requests-tab">
                                <RideRequests />

                            </div>
                            <div className="tab-pane fade" id="nav-history" role="tabpanel"
                                 aria-labelledby="nav-history-tab">

                                <HistoryRides />

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }





};



export default MyRides;