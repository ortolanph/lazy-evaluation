# Lazy Evaluation

Some resources:

## Data diagram

![Data Diagram](diagram.png)

## A small poem

> Data once alive
>
> Shaped, mapped, filtered through its paths—
>
> Parallel in flight
>
> Yet no end would call its name
>
>And the final call found none

## Time table

### Data

| Method                  |  `A`  |  `B`  |  `C`  |  `D`  |  `E`   |  `F`  |  `G`  |  `H`  |
|-------------------------|:-----:|:-----:|:-----:|:-----:|:------:|:-----:|:-----:|:-----:|
| Without Lazy Evaluation | 1.630 | 0.984 | 0.345 | 6.566 | 12.411 | 1.508 | 0.604 | 0.510 |
| With Lazy Evaluation    | 0.846 | 0.783 | 0.207 | 1.544 | 11.250 | 1.086 | 0.139 | 0.288 |

* time in milliseconds

**Data in CSV**:

```csv
Method,ALL COUNTRIES,STORES BY COUNTRY,LOCATIONS BY COUNTRY,SECTIONS BY STORE,TOTAL REVENUE,TOTAL REVENUE BY COUNTRY,TOTAL REVENUE BY COUNTRY AND STORE,TOTAL REVENUE BY COUNTRY AND STORE AND SECTION
Without Lazy Evaluation,1.630,0.984,0.345,6.566,12.411,1.508,0.604,0.510
With Lazy Evaluation,0.846,0.783,0.207,1.544,11.250,1.086,0.139,0.288

```

![graph.png](graph.png)

### Legend

* `A` - ALL COUNTRIES
* `B` - STORES BY COUNTRY
* `C` - LOCATIONS BY COUNTRY
* `D` - SECTIONS BY STORE
* `E` - TOTAL REVENUE
* `F` - TOTAL REVENUE BY COUNTRY
* `G` - TOTAL REVENUE BY COUNTRY AND STORE
* `H` - TOTAL REVENUE BY COUNTRY AND STORE AND SECTION
