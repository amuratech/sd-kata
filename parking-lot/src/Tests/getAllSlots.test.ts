import getAllSlots from "../Services/getAllSlots";
import axios from "axios";

jest.mock('axios');
const mockAxios = axios as jest.Mocked<typeof axios>;

describe("getAllSlots testing", () => {
    it("get call returns all occupied slots", () => {
        mockAxios.get.mockImplementationOnce(() => Promise.resolve({
            data:[{'vehicleNo': "MH24 1266", 'parkingSlot': 1}]
        }))
        const response = getAllSlots();
        response.then(res => {expect(res.data).toStrictEqual([{'vehicleNo': "MH24 1266", 'parkingSlot': 1}])})
                .catch(error => console.log(error))
        expect(mockAxios.get).toHaveBeenCalledTimes(1);
    });
});
