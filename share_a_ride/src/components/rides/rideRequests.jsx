import React, {useEffect, useState} from "react";
import RepositoryService from "../../custom-axios/repository";
import RequestItem from "./requestItem";
import ReactPaginate from "react-paginate";

const RideRequests = (props) => {

    const [requests, setRequests] = useState([]);

    useEffect(() => {
        loadItems()
    }, []);


    function loadItems(page = 0) {
        RepositoryService.getRideRequests(page, 5)
            .then((response) => {
                console.log("get Requests");
                console.log(response);
                setRequests(response.data)
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
                               forcePage={requests.page}
                               pageCount={requests.totalPages}
                               onPageChange={handlePageClick}
                />
            )
        }
    };


    function RequestDataChanged(requestId, status) {
        let newRequests = [];
        if (requests.content != null) {
            newRequests = requests.content.map((item) => {
                if(item.requestId === requestId) {
                    item.responseStatus = status
                }
                return item
            });
            setRequests((prevState) => {
                return {...prevState, content: newRequests}
            })
        }
    }

    function showRequests() {
        let data;
        if (requests.content != null) {
            data = requests.content.map((item) => {
                return (
                    <RequestItem
                        key={item.requestId}
                        data={item}
                        RequestDataChanged={RequestDataChanged}
                    />
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

            {showRequests()}

        </div>
    );
};
export default RideRequests;