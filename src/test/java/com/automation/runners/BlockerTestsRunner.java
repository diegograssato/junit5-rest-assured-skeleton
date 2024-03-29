package com.automation.runners;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;


@Suite
@SelectPackages("com.automation.test")
@SuiteDisplayName("Blocker suite tests")
@IncludeTags("blocker")
public class BlockerTestsRunner {
}