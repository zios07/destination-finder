export interface Statistics {

  requestCount: number;
  request200Count: number;
  request4XXCount: number;
  request5XXCount: number;
  totalResponseTimeMillis: number;
  avgResponseTimeMillis: number;
  maxResponseTimeMillis: number;

}
