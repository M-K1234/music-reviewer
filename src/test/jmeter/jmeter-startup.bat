rem increase memory size allocated for jmeter tests
set HEAP=-Xms1024m -Xmx4096m

rem delete test-results file and contents of results directory before running jmeter 
@echo off
if exist result (
  del /q "result\*.*"
  for /d %%i in ("result\*") do rd /s /q "%%i"
)
if exist test-results.csv (
  del /q "test-results.csv"
)
echo Cleanup complete.
pause

rem run jmeter test plan and log results
jmeter -n -t "MusicReviewerTestPlan.jmx" -l test-results.csv -j log-file.log -e -o result 
rem -p user.properties