import React, {useEffect, useState} from "react";
import {useHistory} from "react-router";
import RepositoryService from "../../custom-axios/repository";
import RideItem from "./rideItem";
import ReactPaginate from "react-paginate";


const HistoryRides = (props) => {

    const [historyRides, setHistoryRides] = useState([]);


    const history = useHistory();

    useEffect(() => {
        loadItems()

    }, []);


    function loadItems(page=0) {
        RepositoryService.getHistoryRides(page,5)
            .then((response) => {
                console.log("get HistoryRides");
                console.log(response);
                setHistoryRides(response.data)
            }).catch(RepositoryService.handleServerError);
    }


    const handlePageClick = (e) => {
        loadItems(e.selected)
    };

    const paginate = () => {
        if (props.totalPages !== 0) {
            return (
                <ReactPaginate previousLabel={"previous"}
                               nextLabel={"next"}
                               breakLabel={<span className="gap">...</span>}
                               breakClassName={"break-me"}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               pageClassName={"page-item"}
                               pageLinkClassName={"page-link"}
                               previousClassName={"page-item"}
                               nextClassName={"page-item"}
                               previousLinkClassName={"page-link"}
                               nextLinkClassName={"page-link"}
                               containerClassName={"pagination justify-content-center"}
                               activeClassName={"active"}
                               forcePage={historyRides.page}
                               pageCount={historyRides.totalPages}
                               onPageChange={handlePageClick}
                />
            )
        }
    }


    function showHistory() {
        let data;
        if(historyRides.content!=null){
            data = historyRides.content.map((item) => {
                return (
                    <RideItem key={item.requestId}  data={item}/>
                );
            });
        }
        return (
            <div>
                {data}
                {paginate()}
            </div>
        )
    }

    return (
        <div>
            {showHistory()}
        </div>
    );
};
export default HistoryRides;