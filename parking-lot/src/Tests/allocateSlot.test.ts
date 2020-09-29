import allocateSlot from "../Services/allocateSlot";
import axios from "axios";

jest.mock('axios');
const mockAxios = axios as jest.Mocked<typeof axios>;

describe("allocateSlot testing", () => {
    it("return allocated slot", () => {
        mockAxios.post.mockImplementationOnce(() => Promise.resolve({
            data:{"slotNo":1,"vehicle":{"vehicleNo":"MH12 5623"}}
        }))
        const response = allocateSlot('MH24 1266');
        response.then(res => {expect(res.data).toStrictEqual({"slotNo":1,"vehicle":{"vehicleNo":"MH12 5623"}})})
                .catch(error => console.log(error))
        expect(mockAxios.post).toHaveBeenCalledTimes(1);
        mockAxios.post.mockClear();
    });

    it("returns server message if parking lot is full", () => {
        mockAxios.post.mockImplementationOnce(() => Promise.reject({
            data:{'message': "Parking Lot is full"}, status:"400"
        }))
        const response = allocateSlot('MH24 1266');
        response.then(() => {})
                .catch(res => expect(res.data.message).toBe("Parking Lot is full"));
        expect(mockAxios.post).toHaveBeenCalledTimes(1);
        mockAxios.post.mockClear();
    });
});
