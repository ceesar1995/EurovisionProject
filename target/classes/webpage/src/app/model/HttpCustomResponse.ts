import {City} from "./City";

export interface HttpCustomResponse {
  content: City[];
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  number: number;
}
